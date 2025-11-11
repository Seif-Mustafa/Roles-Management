package RolesManagement.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="app_page_role")
public class AppPageRole {

    @Column(name="page_id")
    private Long pageId;

    @Column(name="role_id")
    private Long roleId;

    @Column(name="action_by")
    private Long actionBy;

    @Column(name="action_on")
    private Timestamp actionOn;
}
