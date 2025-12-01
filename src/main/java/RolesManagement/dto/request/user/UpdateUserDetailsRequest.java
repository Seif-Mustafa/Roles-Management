package RolesManagement.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDetailsRequest {
    private Long userId;
    @NotEmpty(message = "Username can't be empty")
    private String username;
    @Email(message = "Wrong email format")
    private String email;
    @NotNull
    private char isActive;
    @NotNull
    private Long modifiedBy;

    private List<UpdateUserDetailsRequest.UserDetailsRole> roles;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDetailsRole {
        @NotEmpty
        private Long roleId;
        @NotEmpty
        private char isSelected;
    }

}
