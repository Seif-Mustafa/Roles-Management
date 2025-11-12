package RolesManagement.controller;

import RolesManagement.dto.generic.ApiResponse;
import RolesManagement.dto.request.CreateButtonRoleRequest;
import RolesManagement.dto.request.DeleteButtonRoleRequest;
import RolesManagement.model.AppButtonRole;
import RolesManagement.service.ButtonRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/button-role")
public class ButtonRoleController {
    @Autowired
    ButtonRoleService buttonRoleService;

    @PostMapping
    public ResponseEntity<ApiResponse<AppButtonRole>> createButtonRole(@RequestBody CreateButtonRoleRequest createButtonRoleRequest) {
        AppButtonRole appButtonRole = buttonRoleService.createButtonRole(createButtonRoleRequest);
        return ApiResponse.created(appButtonRole, "Button Role Created Successfully");
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<AppButtonRole>> deleteButtonRole(@RequestBody DeleteButtonRoleRequest deleteButtonRoleRequest) {
        AppButtonRole appButtonRole = buttonRoleService.deleteButtonRole(deleteButtonRoleRequest);

        return ApiResponse.success(appButtonRole, "Button Role Deleted Successfully");
    }

}
