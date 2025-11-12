package RolesManagement.model;

import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_page_role")
public class AppPageRole {

    @EmbeddedId
    private AppPageRoleId id;

    @Column(name = "action_by")
    private Long actionBy;

    @Column(name = "action_on")
    private Timestamp actionOn;

    public Long getPageId() {
        return id != null ? id.getPageId() : null;
    }

    public Long getRoleId() {
        return id != null ? id.getRoleId() : null;
    }
}
