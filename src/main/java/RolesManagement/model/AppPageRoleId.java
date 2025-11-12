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
public class AppPageRoleId implements Serializable {
    @Column(name = "page_id")
    private Long pageId;

    @Column(name = "role_id")
    private Long roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return true;
        AppPageRoleId that = (AppPageRoleId) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(pageId, that.pageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageId, roleId);
    }

}
