package RolesManagement.controller;

import RolesManagement.dto.generic.ApiResponse;
import RolesManagement.dto.request.CreateUserRequest;
import RolesManagement.dto.request.UpdateUserRequest;
import RolesManagement.model.AppUser;
import RolesManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
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
    public ResponseEntity<ApiResponse<List<AppUser>>> getAllUsers(){
        return ApiResponse.success(userService.getAllUsers(), "Users Returned Successfully");
    }

    @GetMapping("/test-user")
    public String userTest() {
        return "User controller is working ";
    }
}
