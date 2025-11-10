package RolesManagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserRoleId implements Serializable {
    @Column(name="user_id")
    private Long userId;

    @Column(name="role_id")
    private Long roleId;

    @Override
    public boolean equals(Object o){
        if(this==o)return true;
        if(o==null || getClass()!=o.getClass())return false;
        AppUserRoleId that = (AppUserRoleId)o;
        return Objects.equals(userId,that.userId)&&
                Objects.equals(roleId,that.roleId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(userId,roleId);
    }

}
