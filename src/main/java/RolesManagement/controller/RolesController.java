package RolesManagement.controller;

import RolesManagement.dto.generic.ApiResponse;
import RolesManagement.dto.request.CreateRoleRequest;
import RolesManagement.dto.request.UpdateRoleDetailsRequest;
import RolesManagement.dto.response.RoleButtonsResponse;
import RolesManagement.dto.response.RoleDetailsResponse;
import RolesManagement.dto.response.RolePagesResponse;
import RolesManagement.dto.response.RoleUsersResponse;
import RolesManagement.model.AppRole;
import RolesManagement.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    RolesService rolesService;

    @PostMapping
    public ResponseEntity<ApiResponse<AppRole>> createRole(@RequestBody CreateRoleRequest createRoleRequest) {
        AppRole appRole = rolesService.createRole(createRoleRequest);
        return ApiResponse.created(appRole, "Role Created Successfully");
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<ApiResponse<AppRole>> getRoleById(@PathVariable Long roleId) {
        AppRole appRole = rolesService.getRoleById(roleId);
        return ApiResponse.success(appRole, "Role Returned Successfully");
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<ApiResponse<AppRole>> updateRole(@PathVariable Long roleId, @RequestBody CreateRoleRequest createRoleRequest) {
        AppRole appRole = rolesService.updateRole(roleId, createRoleRequest);
        return ApiResponse.success(appRole, "Role Updated Successfully");
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<ApiResponse<AppRole>> deleteRole(@PathVariable Long roleId) {
        AppRole appRole = rolesService.deleteRole(roleId);
        return ApiResponse.success(appRole, "Role Deleted Successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AppRole>>> getAllRoles() {
        List<AppRole> allRoles = rolesService.getAllRoles();
        return ApiResponse.success(allRoles, "All Roles Returned Successfully");
    }

    @GetMapping("/{roleId}/users")
    public ResponseEntity<ApiResponse<RoleUsersResponse>> getRoleUsers(@PathVariable Long roleId) {
        RoleUsersResponse roleUsersResponse = rolesService.getRoleUsers(roleId);
        return ApiResponse.success(roleUsersResponse, "Role Users Returned Successfully");
    }

    @GetMapping("/{roleId}/pages")
    public ResponseEntity<ApiResponse<RolePagesResponse>> getRolePages(@PathVariable Long roleId) {
        RolePagesResponse rolePagesResponse = rolesService.getRolePages(roleId);
        return ApiResponse.success(rolePagesResponse, "Role Pages Returned Successfully");
    }


    @GetMapping("/{roleId}/buttons")
    public ResponseEntity<ApiResponse<RoleButtonsResponse>> getRoleButtons(@PathVariable Long roleId){
        RoleButtonsResponse roleButtonsResponse = rolesService.getRoleButtons(roleId);
        return ApiResponse.success(roleButtonsResponse,"Role Buttons Returned Successfully");
    }

    @GetMapping("/role-details/{roleId}")
    public ResponseEntity<ApiResponse<RoleDetailsResponse>> getRoleDetails(@PathVariable Long roleId){
        RoleDetailsResponse roleDetailsResponse = rolesService.getRoleDetails(roleId);
        return ApiResponse.success(roleDetailsResponse,"Role Details Returned Successfully");
    }

    @PutMapping("/role-details/{roleId}")
    public ResponseEntity<ApiResponse<RoleDetailsResponse>> saveRoleDetails(@PathVariable Long roleId, @RequestBody UpdateRoleDetailsRequest updateRoleDetailsRequest){
        RoleDetailsResponse roleDetailsResponse = rolesService.saveRoleDetails(roleId,updateRoleDetailsRequest);

        return ApiResponse.success(roleDetailsResponse,"Role Details Saved Successfully");
    }
}
