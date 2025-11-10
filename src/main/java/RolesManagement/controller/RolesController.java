package RolesManagement.controller;

import RolesManagement.dto.generic.ApiResponse;
import RolesManagement.dto.request.CreateRoleRequest;
import RolesManagement.model.AppRole;
import RolesManagement.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    RolesService rolesService;

    @PostMapping
    public ResponseEntity<ApiResponse<AppRole>> createRole(@RequestBody CreateRoleRequest createRoleRequest){
        AppRole appRole = rolesService.createRole(createRoleRequest);
        return ApiResponse.created(appRole,"Role Created Successfully");
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<ApiResponse<AppRole>> getRoleById(@PathVariable Long roleId){
        AppRole appRole = rolesService.getRole(roleId);
        return ApiResponse.success(appRole,"Role Returned Successfully");
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<ApiResponse<AppRole>> updateRole(@PathVariable Long roleId, @RequestBody CreateRoleRequest createRoleRequest){
        AppRole appRole = rolesService.updateRole(roleId,createRoleRequest);
        return ApiResponse.success(appRole,"Role Updated Successfully");
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<ApiResponse<AppRole>> deleteRole(@PathVariable Long roleId){
        AppRole appRole = rolesService.deleteRole(roleId);
        return ApiResponse.success(appRole,"Role Deleted Successfully");
    }

}
