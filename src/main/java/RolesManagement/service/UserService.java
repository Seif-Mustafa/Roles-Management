package RolesManagement.service;

import RolesManagement.dto.request.CreateUserRequest;
import RolesManagement.dto.request.UpdateUserRequest;
import RolesManagement.dto.request.UserChangePasswordRequest;
import RolesManagement.dto.request.user.UpdateUserDetailsRequest;
import RolesManagement.dto.response.UserButtonsResponse;
import RolesManagement.dto.response.UserPagesResponse;
import RolesManagement.dto.response.UserRolesResponse;
import RolesManagement.dto.response.user.UserDetailsResponse;
import RolesManagement.model.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    AppUser createUser(CreateUserRequest createUserRequest);

    AppUser updateUser(Long userId, UpdateUserRequest updateUserRequest);

    AppUser deleteUser(Long userId);

    AppUser getUserById(Long userId);

    List<AppUser> getAllUsers();

    Page<AppUser> getAllUsersPagination(Pageable pageable);

    List<AppUser> getActiveUsers();

    List<AppUser> getInActiveUsers();

    UserRolesResponse getUserRoles(Long userId);

    UserPagesResponse getUserPages(Long userId);

    UserButtonsResponse getUserButtons(Long userId);

    AppUser userChangePassword(Long userId, UserChangePasswordRequest userChangePasswordRequest);

    UserDetailsResponse getUserDetails(Long userId);

    UserDetailsResponse saveUserDetails(Long userId, UpdateUserDetailsRequest updateUserDetailsRequest);

}
