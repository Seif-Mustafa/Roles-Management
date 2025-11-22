package RolesManagement.model;

import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_page_role")
public class AppPageRole {

    @EmbeddedId
    private AppPageRoleId id;

    @Column(name = "created_by")
    private Long createdBy;

    @CreationTimestamp
    @Column(name = "created_on")
    private Timestamp createdOn;

    public Long getPageId() {
        return id != null ? id.getPageId() : null;
    }

    public Long getRoleId() {
        return id != null ? id.getRoleId() : null;
    }
}
