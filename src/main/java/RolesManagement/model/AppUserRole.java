package RolesManagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="app_user_role")
public class AppUserRole {

    @Column(name="user_id")
    private Long userId;

    @Column(name="role_id")
    private Long roleId;

    @Column(name = "action_by")
    private Long actionBy;

    @Column(name="action_on")
    private Timestamp actionOn;
}
