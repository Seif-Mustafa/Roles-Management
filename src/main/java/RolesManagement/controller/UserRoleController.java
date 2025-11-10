package RolesManagement.controller;

import RolesManagement.dto.generic.ApiResponse;
import RolesManagement.dto.request.AddUserRoleRequest;
import RolesManagement.dto.response.UserRoleResponse;
import RolesManagement.model.AppUser;
import RolesManagement.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-role")
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;

//    @PostMapping("/{userId}/role")
//    public ResponseEntity<ApiResponse<AppUser>> addRoleToUser(@PathVariable Long userId, @RequestBody AddUserRoleRequest addUserRoleRequest){
//
////        UserRoleResponse userRolesResponse = userRoleService;
//
//
//
//    }

}
