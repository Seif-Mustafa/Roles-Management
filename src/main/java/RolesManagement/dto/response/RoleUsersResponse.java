package RolesManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleUsersResponse {
    private Long roleId;
    private String roleName;
    private String description;
    private List<UserResponse> users;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResponse{
        private Long userId;
        private String username;
    }
}
