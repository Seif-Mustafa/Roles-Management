package RolesManagement.service.serviceImpl;

import RolesManagement.dto.request.auth.ForgetPasswordRequest;
import RolesManagement.dto.request.auth.UserLoginRequest;
import RolesManagement.dto.response.UserButtonsResponse;
import RolesManagement.dto.response.UserLoginResponse;
import RolesManagement.dto.response.UserPagesResponse;
import RolesManagement.mapper.UserMapper;
import RolesManagement.model.AppUser;
import RolesManagement.repository.UserRepository;
import RolesManagement.service.AuthService;
import RolesManagement.utils.EmailService;
import RolesManagement.utils.JwtService;
import RolesManagement.utils.RandomPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserMapper userMapper;

    @Autowired
    private final RandomPasswordGenerator randomPasswordGenerator;

    @Autowired
    private final EmailService emailService;

    @Autowired
    private JwtService jwtService;


    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           UserMapper userMapper, RandomPasswordGenerator randomPasswordGenerator,
                           EmailService emailService, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.randomPasswordGenerator = randomPasswordGenerator;
        this.emailService = emailService;
        this.jwtService = jwtService;
    }

    @Override
    public UserLoginResponse loginUser(UserLoginRequest userLoginRequest) {
        AppUser appUser = userRepository.findByAppUsername(userLoginRequest.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(userLoginRequest.getPassword(), appUser.getAppPassword())) {
            throw new RuntimeException("Password is wrong");
        } else if (appUser.getIsActive() == 'N') {
            throw new RuntimeException("This account is inactive");
        }

        List<UserPagesResponse.PageResponse> userPages = userRepository.getUserActivePages(appUser.getUserId());

        List<UserButtonsResponse.ButtonResponse> userButtons = userRepository.getUserActiveButtons(appUser.getUserId());

        String token = jwtService.generateToken(appUser.getAppUsername());

        return userMapper.toDto(appUser, userPages, userButtons,token);
    }

    @Override
    public void forgetPassword(ForgetPasswordRequest forgetPasswordRequest) {
        AppUser appUser = userRepository.findByAppUsernameAndEmail(forgetPasswordRequest.getUsername(), forgetPasswordRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        String newPassword = randomPasswordGenerator.generateSecurePassword(8);

        appUser.setAppPassword(passwordEncoder.encode(newPassword));
        appUser.setModifiedBy(appUser.getUserId());
        userRepository.save(appUser);
        emailService.forgetPasswordMailSending(appUser.getEmail(), appUser.getAppUsername(), newPassword);

    }
}
