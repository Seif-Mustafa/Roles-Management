package RolesManagement.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesResponse {
    private Long userId;
    private String username;

    private List<AppRoleResponse> roles;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AppRoleResponse{
        private Long roleId;
        private String roleName;
        private String description;
        private char isActive;
    }
}
