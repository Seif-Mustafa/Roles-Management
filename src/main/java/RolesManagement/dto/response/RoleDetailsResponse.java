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
public class RoleDetailsResponse {
    private Long roleId;
    private String roleName;
    private String roleDescription;
    private char isActive;
    private List<RoleDetailsPage> pages;
    private List<RoleDetailsButton> buttons;
    private List<RoleDetailsUser> users;


    @Data
    @NoArgsConstructor
//    @AllArgsConstructor
    public static class RoleDetailsPage{
        private Long pageId;
        private String pageName;
        private char isActive;
        private char isSelected;
        public RoleDetailsPage(Long pageId, String pageName, char isActive,String isSelected){
            this.pageId = pageId;
            this.pageName = pageName;
            this.isActive = isActive;
            this.isSelected = isSelected.charAt(0);
        }

    }

    @Data
    @NoArgsConstructor
//    @AllArgsConstructor
    public static class RoleDetailsButton{
        private Long buttonId;
        private String buttonName;
        private char isActive;
        private char isSelected;
        public RoleDetailsButton(Long buttonId, String buttonName, char isActive, String isSelected){
            this.buttonId = buttonId;
            this.buttonName = buttonName;
            this.isActive = isActive;
            this.isSelected = isSelected.charAt(0);
        }
    }

    @Data
    @NoArgsConstructor
//    @AllArgsConstructor
    public static class RoleDetailsUser{
        private Long userId;
        private String username;
        private char isActive;
        private char isSelected;

        public RoleDetailsUser(Long userId, String username, char isActive, String isSelected){
            this.userId = userId;
            this.username = username;
            this.isActive = isActive;
            this.isSelected = isSelected.charAt(0);
        }
    }

}
