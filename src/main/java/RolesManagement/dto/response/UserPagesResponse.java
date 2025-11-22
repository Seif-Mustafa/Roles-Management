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
public class UserPagesResponse {
    private Long userId;
    private String username;
    private List<PageResponse> pages;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageResponse{
        private Long pageId;
        private String pageName;
        private String resourceCode;
        private Long parentPageId;
        private char isActive;
    }
}
