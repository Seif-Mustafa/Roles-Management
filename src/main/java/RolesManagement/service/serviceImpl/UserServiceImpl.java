package RolesManagement.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public AppUser createUser(CreateUserRequest createUserRequest) {
        AppUser createdUser = userRepository.save(userMapper.toEntity(createUserRequest));
        return createdUser;
    }

    @Override
    public AppUser getUserById(Long userId) {
        Optional<AppUser> user = userRepository.findById(userId);
        return user.isPresent() ? user.get() : null;
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

    public List<AppUser> getActiveUsers() {
        return userRepository.findByIsActive('Y');
    }

    public List<AppUser> getInActiveUsers() {
        return userRepository.findByIsActive('N');
    }

    @Override
    public UserRolesResponse getUserRoles(Long userId) {
        AppUser appUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<UserRolesResponse.AppRoleResponse> roles = userRepository.getUserRoles(userId);
        UserRolesResponse userRolesResponse = new UserRolesResponse();
        userRolesResponse.setUserId(appUser.getUserId());
        userRolesResponse.setUsername(appUser.getAppUsername());
        userRolesResponse.setRoles(roles);
        return userRolesResponse;
    }


    @Override
    public UserPagesResponse getUserPages(Long userId) {
        AppUser appUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<UserPagesResponse.PageResponse> userPages = userRepository.getUserPages(userId);
        UserPagesResponse userPagesResponse = new UserPagesResponse();
        userPagesResponse.setUserId(appUser.getUserId());
        userPagesResponse.setUsername(appUser.getAppUsername());
        userPagesResponse.setPages(userPages);
        return userPagesResponse;
    }

    @Override
    public UserButtonsResponse getUserButtons(Long userId) {
        AppUser appUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<UserButtonsResponse.ButtonResponse> userButtons = userRepository.getUserButtons(userId);
        UserButtonsResponse userButtonsResponse = new UserButtonsResponse();
        userButtonsResponse.setUserId(appUser.getUserId());
        userButtonsResponse.setUsername(appUser.getAppUsername());
        userButtonsResponse.setButtons(userButtons);
        return userButtonsResponse;
    }

}
