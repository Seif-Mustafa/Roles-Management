package RolesManagement.mapper;

import RolesManagement.dto.request.CreateRoleRequest;
import RolesManagement.model.AppRole;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class RoleMapper {
    public AppRole toEntity(CreateRoleRequest createRoleRequest){
        AppRole appRole = new AppRole();
        appRole.setRoleName(createRoleRequest.getRoleName());
        appRole.setDescription(createRoleRequest.getDescription());
        appRole.setActionBy(createRoleRequest.getActionBy());
        appRole.setActionOn(Timestamp.from(Instant.now()));
        return appRole;
    }

    public AppRole toEntity(Long roleId,CreateRoleRequest createRoleRequest){
        AppRole appRole = new AppRole();
        appRole.setRoleId(roleId);
        appRole.setRoleName(createRoleRequest.getRoleName());
        appRole.setDescription(createRoleRequest.getDescription());
        appRole.setActionBy(createRoleRequest.getActionBy());
        appRole.setActionOn(Timestamp.from(Instant.now()));
        return appRole;
    }
}
