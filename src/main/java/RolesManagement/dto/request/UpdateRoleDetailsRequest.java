package RolesManagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoleDetailsRequest {
    private Long roleId;
    private String roleName;
    private String roleDescription;
    private char isActive;
    private Long modifiedBy;
    private List<RoleDetailsPage> pages;
    private List<RoleDetailsButton> buttons;
    private List<RoleDetailsUser> users;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoleDetailsPage {
        private Long pageId;
        private char isSelected;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoleDetailsButton {
        private Long buttonId;
        private char isSelected;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RoleDetailsUser {
        private Long userId;
        private char isSelected;

    }

}
