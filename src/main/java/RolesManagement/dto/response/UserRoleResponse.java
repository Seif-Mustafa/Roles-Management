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
public class UserRoleResponse {
    private Long userId;

    private String username;

    private List<AppRoleResponse> userRoles;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class AppRoleResponse{
        private Long roleId;
        private String roleName;
        private String roleDescription;
    }
}
