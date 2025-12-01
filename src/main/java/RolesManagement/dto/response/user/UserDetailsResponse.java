package RolesManagement.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsResponse {
    private Long userId;
    private String username;
    private String email;
    private char isActive;
    private List<UserRole> roles;

    @Data
    @NoArgsConstructor
    public static class UserRole {
        private Long roleId;
        private String roleName;
        private String description;
        private char isActive;
        private char isSelected;

        public UserRole(Long roleId, String roleName, String description, char isActive, String isSelected) {
            this.roleId = roleId;
            this.roleName = roleName;
            this.description = description;
            this.isActive = isActive;
            this.isSelected = isSelected.charAt(0);
        }
    }
}
