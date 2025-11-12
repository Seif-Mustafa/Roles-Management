package RolesManagement.mapper;

import RolesManagement.dto.request.CreateUserRoleRequest;
import RolesManagement.dto.request.DeleteUserRoleRequest;
import RolesManagement.model.AppUserRole;
import RolesManagement.model.AppUserRoleId;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class UserRoleMapper {

    public AppUserRole toEntity(CreateUserRoleRequest createUserRoleRequest){
        AppUserRoleId appUserRoleId = new AppUserRoleId();
        appUserRoleId.setRoleId(createUserRoleRequest.getRoleId());
        appUserRoleId.setUserId(createUserRoleRequest.getUserId());
        AppUserRole appUserRole = new AppUserRole();
        appUserRole.setId(appUserRoleId);
        appUserRole.setActionBy(createUserRoleRequest.getActionBy());
        appUserRole.setActionOn(Timestamp.from(Instant.now()));
        return appUserRole;
    }

    public AppUserRoleId toEntity(DeleteUserRoleRequest deleteUserRoleRequest){
        AppUserRoleId appUserRoleId = new AppUserRoleId();
        appUserRoleId.setUserId(deleteUserRoleRequest.getUserId());
        appUserRoleId.setRoleId(deleteUserRoleRequest.getRoleId());
        return appUserRoleId;
    }

}
