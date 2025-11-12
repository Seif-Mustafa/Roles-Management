package RolesManagement.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_button_role")
public class AppButtonRole {

    @EmbeddedId
    private AppButtonRoleId id;

    @Column(name = "action_by")
    private Long actionBy;

    @Column(name = "action_on")
    private Timestamp actionOn;

    public Long getButtonId() {
        return id != null ? id.getButtonId() : null;
    }

    public Long getRoleId() {
        return id != null ? id.getRoleId() : null;
    }

}
