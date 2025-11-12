package RolesManagement.controller;

import java.util.List;

import RolesManagement.dto.response.UserButtonsResponse;
import RolesManagement.dto.response.UserPagesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import RolesManagement.dto.generic.ApiResponse;
import RolesManagement.dto.request.CreateUserRequest;
import RolesManagement.dto.request.UpdateUserRequest;
import RolesManagement.dto.response.UserRolesResponse;
import RolesManagement.model.AppUser;
import RolesManagement.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<AppUser>> createUser(@RequestBody CreateUserRequest createUserRequest) {
        AppUser createdUser = userService.createUser(createUserRequest);
        return ApiResponse.created(createdUser, "User Created Successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<AppUser>> getUserById(@PathVariable Long userId) {
        AppUser appUser = userService.getUserById(userId);
        return ApiResponse.success(appUser, "User Returned Successfully");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<AppUser>> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest updateUserRequest) {
        AppUser updatedUser = userService.updateUser(userId, updateUserRequest);
        return ApiResponse.success(updatedUser, "User Updated Successfully");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<AppUser>> deleteUser(@PathVariable Long userId) {
        AppUser deletedUser = userService.deleteUser(userId);
        return ApiResponse.success(deletedUser, "User Deleted Successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AppUser>>> getAllUsers() {
        return ApiResponse.success(userService.getAllUsers(), "Users Returned Successfully");
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<AppUser>>> getActiveUsers() {
        return ApiResponse.success(userService.getActiveUsers(), "Active Users Returned Successfully");
    }

    @GetMapping("/in-active")
    public ResponseEntity<ApiResponse<List<AppUser>>> getInActiveUsers() {
        return ApiResponse.success(userService.getInActiveUsers(), "InActive Users Returned Successfully");
    }

    @GetMapping("/{userId}/roles")
    public ResponseEntity<ApiResponse<UserRolesResponse>> getUserRoles(@PathVariable Long userId) {
        UserRolesResponse userRolesResponse = userService.getUserRoles(userId);

        return ApiResponse.success(userRolesResponse, "User Roles Returned Successfully");
    }

    @GetMapping("/{userId}/pages")
    public ResponseEntity<ApiResponse<UserPagesResponse>> getUserPages(@PathVariable Long userId) {
        UserPagesResponse userPagesResponse = userService.getUserPages(userId);

        return ApiResponse.success(userPagesResponse, "User Pages Returned Successfully");
    }

    @GetMapping("/{userId}/buttons")
    public ResponseEntity<ApiResponse<UserButtonsResponse>> getUserButtons(@PathVariable Long userId) {
        UserButtonsResponse userButtonsResponse = userService.getUserButtons(userId);
        return ApiResponse.success(userButtonsResponse, "User Buttons Returned Successfully");
    }


}
