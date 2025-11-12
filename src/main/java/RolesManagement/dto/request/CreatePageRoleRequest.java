package RolesManagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePageRoleRequest {
    private Long roleId;
    private Long pageId;
    private Long actionBy;
}
