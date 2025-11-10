package RolesManagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="app_user_role")
public class AppUserRole {

    @EmbeddedId
    private AppUserRoleId id;

    @Column(name = "action_by")
    private Long actionBy;

    @Column(name="action_on")
    private Timestamp actionOn;


    public Long getUserId(){
        return id != null?id.getUserId():null;
    }

    public Long getRoleId(){
        return id !=null?id.getRoleId():null;
    }
}
