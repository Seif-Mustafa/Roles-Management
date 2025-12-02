package RolesManagement.controller;

import java.util.List;

import RolesManagement.dto.request.UserChangePasswordRequest;
import RolesManagement.dto.request.user.UpdateUserDetailsRequest;
import RolesManagement.dto.response.UserButtonsResponse;
import RolesManagement.dto.response.UserPagesResponse;
import RolesManagement.dto.response.user.UserDetailsResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/pagination")
    public ResponseEntity<ApiResponse<Page<AppUser>>> getAllUsersPagination(@PageableDefault(page = 0, size = 5, sort = "userId") Pageable pageable) {
        return ApiResponse.success(userService.getAllUsersPagination(pageable), "Paginated Users Returned Successfully");
    }

    @GetMapping("/pagination-filter")
    public ResponseEntity<ApiResponse<Page<AppUser>>> getUsersPaginationFiltering(
            @PageableDefault(page = 0, size = 5, sort = "userId") Pageable pageable,
            @RequestParam String filter
    ) {
        return ApiResponse.success(userService.getUsersPaginationFiltering(filter, pageable), "Pagination Filtering Returned Successfully");
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

    @PutMapping("/{userId}/change-password")
    public ResponseEntity<ApiResponse<AppUser>> userChangePassword(@PathVariable Long userId, @RequestBody UserChangePasswordRequest userChangePasswordRequest) {
        AppUser appUser = userService.userChangePassword(userId, userChangePasswordRequest);
        return ApiResponse.success(appUser, "Password Changed Successfully");
    }

    @GetMapping("/user-details/{userId}")
    public ResponseEntity<ApiResponse<UserDetailsResponse>> getUserDetails(@PathVariable Long userId) {
        UserDetailsResponse userDetailsResponse = userService.getUserDetails(userId);
        return ApiResponse.success(userDetailsResponse, "User Details Returned Successfully");
    }

    @PutMapping("/user-details/{userId}")
    public ResponseEntity<ApiResponse<UserDetailsResponse>> saveUserDetails(@PathVariable Long userId, @Valid @RequestBody UpdateUserDetailsRequest updateUserDetailsRequest) {
        UserDetailsResponse userDetailsResponse = userService.saveUserDetails(userId, updateUserDetailsRequest);

        return ApiResponse.success(userDetailsResponse, "User Details Saved Successfully");
    }

}
