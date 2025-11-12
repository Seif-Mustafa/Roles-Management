package RolesManagement.mapper;

import RolesManagement.dto.request.CreatePageRoleRequest;
import RolesManagement.dto.request.DeletePageRoleRequest;
import RolesManagement.model.AppPageRole;
import RolesManagement.model.AppPageRoleId;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class PageRoleMapper {
    public AppPageRole toEntity(CreatePageRoleRequest createPageRoleRequest) {
        AppPageRoleId appPageRoleId = new AppPageRoleId();
        appPageRoleId.setPageId(createPageRoleRequest.getPageId());
        appPageRoleId.setRoleId(createPageRoleRequest.getRoleId());
        AppPageRole appPageRole = new AppPageRole();
        appPageRole.setId(appPageRoleId);
        appPageRole.setActionBy(createPageRoleRequest.getActionBy());
        appPageRole.setActionOn(Timestamp.from(Instant.now()));
        return appPageRole;
    }

    public AppPageRoleId toEntity(DeletePageRoleRequest deletePageRoleRequest) {
        AppPageRoleId appPageRoleId = new AppPageRoleId();
        appPageRoleId.setRoleId(deletePageRoleRequest.getRoleId());
        appPageRoleId.setPageId(deletePageRoleRequest.getPageId());
        return appPageRoleId;
    }
}
