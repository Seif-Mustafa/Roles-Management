package RolesManagement.mapper;

import RolesManagement.dto.request.CreateRoleRequest;
import RolesManagement.dto.request.UpdateRoleDetailsRequest;
import RolesManagement.dto.response.RoleDetailsResponse;
import RolesManagement.model.AppRole;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleMapper {
    public AppRole toEntity(CreateRoleRequest createRoleRequest){
        AppRole appRole = new AppRole();
        appRole.setRoleName(createRoleRequest.getRoleName());
        appRole.setDescription(createRoleRequest.getDescription());
        appRole.setIsActive(createRoleRequest.getIsActive());
        appRole.setCreatedBy(createRoleRequest.getActionBy());
        return appRole;
    }

    public AppRole toEntity(Long roleId,CreateRoleRequest createRoleRequest){
        AppRole appRole = new AppRole();
        appRole.setRoleId(roleId);
        appRole.setRoleName(createRoleRequest.getRoleName());
        appRole.setDescription(createRoleRequest.getDescription());
        appRole.setIsActive(createRoleRequest.getIsActive());
        appRole.setModifiedBy(createRoleRequest.getActionBy());
        return appRole;
    }

    public AppRole toEntity(Long roleId, UpdateRoleDetailsRequest updateRoleDetailsRequest){
        // Updating role
        AppRole appRole = new AppRole();
        appRole.setRoleId(roleId);
        appRole.setRoleName(updateRoleDetailsRequest.getRoleName());
        appRole.setDescription(updateRoleDetailsRequest.getRoleDescription());
        appRole.setIsActive(updateRoleDetailsRequest.getIsActive());
        appRole.setModifiedBy(updateRoleDetailsRequest.getModifiedBy());
        return appRole;
    }

    public RoleDetailsResponse toDto(AppRole appRole, List<RoleDetailsResponse.RoleDetailsPage> pages, List<RoleDetailsResponse.RoleDetailsButton> buttons, List<RoleDetailsResponse.RoleDetailsUser> users){
       return RoleDetailsResponse
                .builder()
                .roleId(appRole.getRoleId())
                .roleName(appRole.getRoleName())
                .roleDescription(appRole.getDescription())
                .isActive(appRole.getIsActive())
                .pages(pages)
                .buttons(buttons)
                .users(users)
                .build();
    }
}
