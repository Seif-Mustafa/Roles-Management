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
public class UserLoginResponse {
    private Long userId;
    private String username;
    private String email;
    private List<PermittedPage> pages;
    private List<PermittedButton> buttons;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PermittedPage{
        private Long pageId;
        private String pageName;
        private char isActive;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PermittedButton{
        private Long buttonId;
        private String buttonName;
        private Long pageId;
        private char isActive;
    }
}
