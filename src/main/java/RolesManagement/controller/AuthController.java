package RolesManagement.controller;

import RolesManagement.dto.generic.ApiResponse;
import RolesManagement.dto.request.auth.ForgetPasswordRequest;
import RolesManagement.dto.request.auth.UserLoginRequest;
import RolesManagement.dto.response.UserLoginResponse;
import RolesManagement.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserLoginResponse>> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        UserLoginResponse userLoginResponse = authService.loginUser(userLoginRequest);
        return ApiResponse.success(userLoginResponse,"User Logged In Successfully");
    }

    @PostMapping("/forget-password")
    public ResponseEntity<ApiResponse<String>> forgetPassword(@RequestBody ForgetPasswordRequest forgetPasswordRequest) {
         authService.forgetPassword(forgetPasswordRequest);
        return ApiResponse.success("You received an email with the new password","User Password changed Successfully");
    }


}
