package RolesManagement.service;

import RolesManagement.dto.request.CreateRoleRequest;
import RolesManagement.model.AppRole;

public interface RolesService {
    AppRole createRole(CreateRoleRequest createRoleRequest);

    AppRole getRole(Long roleId);

    AppRole updateRole(Long roleId, CreateRoleRequest createRoleRequest);

    AppRole deleteRole(Long roleId);

}

