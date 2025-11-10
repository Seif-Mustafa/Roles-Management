package RolesManagement.service;

import RolesManagement.dto.request.AddUserRoleRequest;

public interface UserRoleService {
    AddUserRoleRequest createUserRole(AddUserRoleRequest addUserRoleRequest);
}
