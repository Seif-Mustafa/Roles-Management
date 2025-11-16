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
public class RoleButtonsResponse {
    private Long roleId;
    private String roleName;
    private String description;
    List<ButtonResponse> buttons;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ButtonResponse{
        private Long buttonId;
        private String buttonName;
        private Long pageId;
        private char isActive;
    }
}
