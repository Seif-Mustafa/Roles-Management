package RolesManagement.controller;

import RolesManagement.dto.generic.ApiResponse;
import RolesManagement.dto.request.auth.ForgetPasswordRequest;
import RolesManagement.dto.request.auth.UserLoginRequest;
import RolesManagement.dto.response.UserLoginResponse;
import RolesManagement.service.AuthService;
import RolesManagement.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserLoginResponse>> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        UserLoginResponse userLoginResponse = authService.loginUser(userLoginRequest);
        return ApiResponse.success(userLoginResponse, "User Logged In Successfully");
    }

    @PostMapping("/forget-password")
    public ResponseEntity<ApiResponse<String>> forgetPassword(@RequestBody ForgetPasswordRequest forgetPasswordRequest) {
        authService.forgetPassword(forgetPasswordRequest);
        return ApiResponse.success("You received an email with the new password", "User Password changed Successfully");
    }


}
