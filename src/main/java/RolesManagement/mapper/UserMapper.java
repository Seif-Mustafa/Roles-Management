package RolesManagement.mapper;

import RolesManagement.dto.request.CreateUserRequest;
import RolesManagement.dto.request.UpdateUserRequest;
import RolesManagement.model.AppUser;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class UserMapper {

    public AppUser toEntity(CreateUserRequest dto) {
        AppUser user = new AppUser();
        user.setAppUsername(dto.getAppUsername());
        user.setAppPassword(dto.getAppPassword());
        user.setEmail(dto.getEmail());
        user.setIsActive('Y');
        user.setActionBy(dto.getActionBy());
        user.setActionOn(Timestamp.from(Instant.now()));
        return user;
    }

    public AppUser toEntity(Long userId, UpdateUserRequest dto) {
        AppUser user = new AppUser();
        user.setUserId(userId);
        user.setAppUsername(dto.getAppUsername());
        user.setAppPassword(dto.getAppPassword());
        user.setEmail(dto.getEmail());
        user.setIsActive(dto.getIsActive());
        user.setActionBy(dto.getActionBy());
        user.setActionOn(Timestamp.from(Instant.now()));
        return user;
    }
}

