package RolesManagement.controller;

import RolesManagement.dto.generic.ApiResponse;
import RolesManagement.dto.request.CreateUserRoleRequest;
import RolesManagement.dto.request.DeleteUserRoleRequest;
import RolesManagement.model.AppUserRole;
import RolesManagement.service.UserRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user-role")
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;


    @PostMapping
    public ResponseEntity<ApiResponse<AppUserRole>> createUserRole(@RequestBody CreateUserRoleRequest createUserRoleRequest) {
        AppUserRole appUserRole = userRoleService.createUserRole(createUserRoleRequest);
        return ApiResponse.created(appUserRole, "User Role Created Successfully");
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<AppUserRole>> deleteUserRole(@RequestBody DeleteUserRoleRequest deleteUserRoleRequest) {
        AppUserRole appUserRole = userRoleService.deleteUserRole(deleteUserRoleRequest);
        return ApiResponse.success(appUserRole, "User Role Deleted Successfully");
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<ApiResponse<UserRolesResponse>> getUserRoles(@PathVariable Long userId){
//        UserRolesResponse userRolesResponse = userService.getUserRoles(userId);
//
//        return ApiResponse.success(userRolesResponse, "User Roles Returned Successfully");
//    }

}
