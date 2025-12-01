package RolesManagement.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import RolesManagement.dto.request.UserChangePasswordRequest;
import RolesManagement.dto.request.user.UpdateUserDetailsRequest;
import RolesManagement.dto.response.user.UserDetailsResponse;
import RolesManagement.model.AppUserRole;
import RolesManagement.model.AppUserRoleId;
import RolesManagement.repository.UserRoleRepository;
import RolesManagement.utils.EmailService;
import RolesManagement.utils.RandomPasswordGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import RolesManagement.dto.request.CreateUserRequest;
import RolesManagement.dto.request.UpdateUserRequest;
import RolesManagement.dto.response.UserButtonsResponse;
import RolesManagement.dto.response.UserPagesResponse;
import RolesManagement.dto.response.UserRolesResponse;
import RolesManagement.mapper.UserMapper;
import RolesManagement.model.AppUser;
import RolesManagement.repository.UserRepository;
import RolesManagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final EmailService emailService;

    private final RandomPasswordGenerator randomPasswordGenerator;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
                           EmailService emailService, RandomPasswordGenerator randomPasswordGenerator,
                           PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.emailService = emailService;
        this.randomPasswordGenerator = randomPasswordGenerator;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public AppUser createUser(CreateUserRequest createUserRequest) {
        String userPassword = randomPasswordGenerator.generateSecurePassword(8);
        AppUser createdUser = userRepository.save(userMapper.toEntity(createUserRequest, userPassword));
        emailService.createUserMailSending(createUserRequest, userPassword);

        return createdUser;
    }

    @Override
    public AppUser getUserById(Long userId) {
        AppUser user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }


    @Override
    public AppUser updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        AppUser updatedUser = userRepository.save(userMapper.toEntity(userId, updateUserRequest));
        return updatedUser;
    }

    @Override
    public AppUser deleteUser(Long userId) {
        Optional<AppUser> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return user.get();
        }
        return null;
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public Page<AppUser> getAllUsersPagination(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    public List<AppUser> getActiveUsers() {
        return userRepository.findByIsActive('Y');
    }

    public List<AppUser> getInActiveUsers() {
        return userRepository.findByIsActive('N');
    }

    @Override
    public UserRolesResponse getUserRoles(Long userId) {
        AppUser appUser = getUserById(userId);
        List<UserRolesResponse.AppRoleResponse> roles = userRepository.getUserRoles(userId);
        UserRolesResponse userRolesResponse = new UserRolesResponse();
        userRolesResponse.setUserId(appUser.getUserId());
        userRolesResponse.setUsername(appUser.getAppUsername());
        userRolesResponse.setRoles(roles);
        return userRolesResponse;
    }


    @Override
    public UserPagesResponse getUserPages(Long userId) {
        AppUser appUser = getUserById(userId);
        List<UserPagesResponse.PageResponse> userPages = userRepository.getUserPages(userId);
        UserPagesResponse userPagesResponse = new UserPagesResponse();
        userPagesResponse.setUserId(appUser.getUserId());
        userPagesResponse.setUsername(appUser.getAppUsername());
        userPagesResponse.setPages(userPages);
        return userPagesResponse;
    }

    @Override
    public UserButtonsResponse getUserButtons(Long userId) {
        AppUser appUser = getUserById(userId);
        List<UserButtonsResponse.ButtonResponse> userButtons = userRepository.getUserButtons(userId);
        UserButtonsResponse userButtonsResponse = new UserButtonsResponse();
        userButtonsResponse.setUserId(appUser.getUserId());
        userButtonsResponse.setUsername(appUser.getAppUsername());
        userButtonsResponse.setButtons(userButtons);
        return userButtonsResponse;
    }

    @Override
    public AppUser userChangePassword(Long userId, UserChangePasswordRequest userChangePasswordRequest) {
        AppUser appUser = getUserById(userId);

        if (!passwordEncoder.matches(userChangePasswordRequest.getOldPassword(), appUser.getAppPassword())) {
            throw new RuntimeException("Old Password is wrong");
        }
        appUser.setAppPassword(passwordEncoder.encode(userChangePasswordRequest.getNewPassword()));
        userRepository.save(appUser);

        return appUser;
    }

    @Override
    public UserDetailsResponse getUserDetails(Long userId) {
        AppUser appUser = getUserById(userId);
        List<UserDetailsResponse.UserRole> userRoles = userRepository.getAllRolesByUserId(userId);

        return userMapper.toDto(appUser, userRoles);
    }

    @Override
    @Transactional
    public UserDetailsResponse saveUserDetails(Long userId, UpdateUserDetailsRequest updateUserDetailsRequest) {
        AppUser appUser = getUserById(userId);
        appUser.setAppUsername(updateUserDetailsRequest.getUsername());
        appUser.setEmail(updateUserDetailsRequest.getEmail());
        appUser.setIsActive(updateUserDetailsRequest.getIsActive());
        appUser.setModifiedBy(updateUserDetailsRequest.getModifiedBy());
        // Saving User
        userRepository.save(appUser);

        userRoleRepository.deleteAllByUserId(userId);
        List<AppUserRole> selectedRoles = new ArrayList<>();
        updateUserDetailsRequest.getRoles().forEach((role) -> {
            if (role.getIsSelected() == 'Y') {
                AppUserRoleId appUserRoleId = new AppUserRoleId(userId, role.getRoleId());
                AppUserRole appUserRole = new AppUserRole();
                appUserRole.setId(appUserRoleId);
                appUserRole.setCreatedBy(updateUserDetailsRequest.getModifiedBy());
                selectedRoles.add(appUserRole);
            }
        });
        userRoleRepository.saveAll(selectedRoles);

        return getUserDetails(userId);
    }

}
