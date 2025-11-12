package RolesManagement.repository;

import RolesManagement.model.AppButtonRole;
import RolesManagement.model.AppButtonRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ButtonRoleRepository extends JpaRepository<AppButtonRole, AppButtonRoleId> {
}
