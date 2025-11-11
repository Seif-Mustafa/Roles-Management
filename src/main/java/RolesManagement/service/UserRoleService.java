package RolesManagement.service;

import RolesManagement.dto.request.CreateUserRoleRequest;
import RolesManagement.model.AppUserRole;

public interface UserRoleService {
    AppUserRole createUserRole(CreateUserRoleRequest createUserRoleRequest);

}
