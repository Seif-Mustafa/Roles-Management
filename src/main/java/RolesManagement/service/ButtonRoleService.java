package RolesManagement.service;

import RolesManagement.dto.request.CreateButtonRoleRequest;
import RolesManagement.dto.request.DeleteButtonRoleRequest;
import RolesManagement.model.AppButtonRole;

public interface ButtonRoleService {
    AppButtonRole createButtonRole(CreateButtonRoleRequest createButtonRoleRequest);

    AppButtonRole deleteButtonRole(DeleteButtonRoleRequest deleteButtonRoleRequest);
}
