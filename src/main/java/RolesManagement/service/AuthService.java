package RolesManagement.service;

import RolesManagement.dto.request.auth.ForgetPasswordRequest;
import RolesManagement.dto.request.auth.UserLoginRequest;
import RolesManagement.dto.response.UserLoginResponse;

public interface AuthService {
    UserLoginResponse loginUser(UserLoginRequest userLoginRequest);

    void forgetPassword(ForgetPasswordRequest forgetPasswordRequest);

}
