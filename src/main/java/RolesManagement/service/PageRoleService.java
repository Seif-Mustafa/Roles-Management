package RolesManagement.service;

import RolesManagement.dto.request.CreatePageRoleRequest;
import RolesManagement.dto.request.DeletePageRoleRequest;
import RolesManagement.model.AppPageRole;

public interface PageRoleService {
    AppPageRole createPageRole(CreatePageRoleRequest createRolePageRequest);

    AppPageRole deletePageRole(DeletePageRoleRequest deletePageRoleRequest);
}
