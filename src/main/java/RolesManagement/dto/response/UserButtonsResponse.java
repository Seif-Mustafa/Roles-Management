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
public class UserButtonsResponse {
    private Long userId;
    private String username;
    private List<ButtonResponse> buttons;

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
