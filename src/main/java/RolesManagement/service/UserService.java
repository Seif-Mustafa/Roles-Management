package RolesManagement.service;

import RolesManagement.dto.request.CreateUserRequest;
import RolesManagement.dto.request.UpdateUserRequest;
import RolesManagement.model.AppUser;

import java.util.List;

public interface UserService {
    AppUser createUser(CreateUserRequest createUserRequest);

    AppUser updateUser(Long userId, UpdateUserRequest updateUserRequest);

    AppUser deleteUser(Long userId);

    AppUser getUserById(Long userId);

    List<AppUser> getAllUsers();

}
