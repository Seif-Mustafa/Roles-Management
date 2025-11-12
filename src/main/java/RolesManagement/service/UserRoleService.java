package RolesManagement.service;

import RolesManagement.dto.request.CreateUserRoleRequest;
import RolesManagement.dto.request.DeleteUserRoleRequest;
import RolesManagement.model.AppUserRole;

public interface UserRoleService {
    AppUserRole createUserRole(CreateUserRoleRequest createUserRoleRequest);

    AppUserRole deleteUserRole(DeleteUserRoleRequest deleteUserRoleRequest);
}
