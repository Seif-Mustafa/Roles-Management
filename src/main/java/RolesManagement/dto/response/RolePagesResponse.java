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
public class RolePagesResponse {
    private Long roleId;
    private String roleName;
    private String description;
    List<PageResponse> pages;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageResponse {
        private Long pageId;
        private String pageName;
        private char isActive;
    }
}
