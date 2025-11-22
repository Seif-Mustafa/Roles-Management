package RolesManagement.mapper;

import org.springframework.stereotype.Component;

import RolesManagement.dto.request.CreateUserRoleRequest;
import RolesManagement.dto.request.DeleteUserRoleRequest;
import RolesManagement.model.AppUserRole;
import RolesManagement.model.AppUserRoleId;

@Component
public class UserRoleMapper {

    public AppUserRole toEntity(CreateUserRoleRequest createUserRoleRequest){
        AppUserRoleId appUserRoleId = new AppUserRoleId();
        appUserRoleId.setRoleId(createUserRoleRequest.getRoleId());
        appUserRoleId.setUserId(createUserRoleRequest.getUserId());
        AppUserRole appUserRole = new AppUserRole();
        appUserRole.setId(appUserRoleId);
        appUserRole.setCreatedBy(createUserRoleRequest.getActionBy());
        // appUserRole.setActionOn(Timestamp.from(Instant.now()));
        return appUserRole;
    }

    public AppUserRoleId toEntity(DeleteUserRoleRequest deleteUserRoleRequest){
        AppUserRoleId appUserRoleId = new AppUserRoleId();
        appUserRoleId.setUserId(deleteUserRoleRequest.getUserId());
        appUserRoleId.setRoleId(deleteUserRoleRequest.getRoleId());
        return appUserRoleId;
    }

}
