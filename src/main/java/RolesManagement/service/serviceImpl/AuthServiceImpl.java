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
import RolesManagement.utils.RandomPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final RandomPasswordGenerator randomPasswordGenerator;

    private final EmailService emailService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           UserMapper userMapper, RandomPasswordGenerator randomPasswordGenerator,
                           EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.randomPasswordGenerator = randomPasswordGenerator;
        this.emailService = emailService;
    }

    public UserLoginResponse loginUser(UserLoginRequest userLoginRequest) {
        AppUser appUser = userRepository.findByAppUsername(userLoginRequest.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(userLoginRequest.getPassword(), appUser.getAppPassword())) {
            throw new RuntimeException("Password is wrong");
        }

        List<UserPagesResponse.PageResponse> userPages = userRepository.getUserPages(appUser.getUserId());

        List<UserButtonsResponse.ButtonResponse> userButtons = userRepository.getUserButtons(appUser.getUserId());

        return userMapper.toDto(appUser, userPages, userButtons);
    }

    public void forgetPassword(ForgetPasswordRequest forgetPasswordRequest) {
        AppUser appUser = userRepository.findByAppUsernameAndEmail(forgetPasswordRequest.getUsername(), forgetPasswordRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        String newPassword = randomPasswordGenerator.generateSecurePassword(8);

        appUser.setAppPassword(passwordEncoder.encode(newPassword));
        appUser.setActionBy(appUser.getUserId());
        appUser.setActionOn(Timestamp.from(Instant.now()));
        userRepository.save(appUser);
        emailService.forgetPasswordMailSending(appUser.getEmail(), appUser.getAppUsername(), newPassword);

    }
}
