package RolesManagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {
    private String appUsername;
    private String appPassword;
    private String email;
    private Long actionBy;
}
