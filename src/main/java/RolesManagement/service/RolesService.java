package RolesManagement.service;

import java.util.List;

import RolesManagement.dto.request.CreateRoleRequest;
import RolesManagement.dto.response.RolePagesResponse;
import RolesManagement.dto.response.RoleUsersResponse;
import RolesManagement.model.AppRole;

public interface RolesService {
    AppRole createRole(CreateRoleRequest createRoleRequest);

    AppRole getRole(Long roleId);

    AppRole updateRole(Long roleId, CreateRoleRequest createRoleRequest);

    AppRole deleteRole(Long roleId);

    List<AppRole> getAllRoles();

    RoleUsersResponse getRoleUsers(Long roleId);

    RolePagesResponse getRolePages(Long roleId);

}

