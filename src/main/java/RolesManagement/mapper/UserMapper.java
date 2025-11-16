package RolesManagement.mapper;

import RolesManagement.dto.request.CreateUserRequest;
import RolesManagement.dto.request.UpdateUserRequest;
import RolesManagement.dto.response.UserButtonsResponse;
import RolesManagement.dto.response.UserLoginResponse;
import RolesManagement.dto.response.UserPagesResponse;
import RolesManagement.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser toEntity(CreateUserRequest dto, String password) {
        String hashedPassword = passwordEncoder.encode(password);

        AppUser user = new AppUser();
        user.setAppUsername(dto.getAppUsername());
        user.setAppPassword(hashedPassword);
        user.setEmail(dto.getEmail());
        user.setIsActive(dto.getIsActive());
        user.setActionBy(dto.getActionBy());
        user.setActionOn(Timestamp.from(Instant.now()));
        return user;
    }

    public AppUser toEntity(Long userId, UpdateUserRequest dto) {
        AppUser user = new AppUser();
        user.setUserId(userId);
        user.setAppUsername(dto.getAppUsername());
        user.setEmail(dto.getEmail());
        user.setIsActive(dto.getIsActive());
        user.setActionBy(dto.getActionBy());
        user.setActionOn(Timestamp.from(Instant.now()));
        return user;
    }


    public UserLoginResponse toDto(AppUser appUser, List<UserPagesResponse.PageResponse> userPages, List<UserButtonsResponse.ButtonResponse> userButtons) {
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setUserId(appUser.getUserId());
        userLoginResponse.setUsername(appUser.getAppUsername());
        userLoginResponse.setEmail(appUser.getEmail());

        userLoginResponse.setPages(userPages.stream()
                .map((page) -> new UserLoginResponse.PermittedPage(
                        page.getPageId(),
                        page.getPageName(),
                        page.getIsActive()
                )).collect(Collectors.toList()));

        userLoginResponse.setButtons(userButtons.stream()
                .map((button) -> new UserLoginResponse.PermittedButton(
                        button.getButtonId(),
                        button.getButtonName(),
                        button.getPageId(),
                        button.getIsActive()
                )).collect(Collectors.toList()));

        return userLoginResponse;
    }
}

