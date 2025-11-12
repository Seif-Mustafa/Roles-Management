package RolesManagement.service;

import RolesManagement.dto.request.CreateUserRequest;
import RolesManagement.dto.request.UpdateUserRequest;
import RolesManagement.dto.response.UserButtonsResponse;
import RolesManagement.dto.response.UserPagesResponse;
import RolesManagement.dto.response.UserRolesResponse;
import RolesManagement.model.AppUser;

import java.util.List;

public interface UserService {
    AppUser createUser(CreateUserRequest createUserRequest);

    AppUser updateUser(Long userId, UpdateUserRequest updateUserRequest);

    AppUser deleteUser(Long userId);

    AppUser getUserById(Long userId);

    List<AppUser> getAllUsers();

    List<AppUser> getActiveUsers();

    List<AppUser> getInActiveUsers();

    UserRolesResponse getUserRoles(Long userId);

    UserPagesResponse getUserPages(Long userId);

    UserButtonsResponse getUserButtons(Long userId);
}
