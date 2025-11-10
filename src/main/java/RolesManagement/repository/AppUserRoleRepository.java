package RolesManagement.repository;

import RolesManagement.model.AppUserRole;
import RolesManagement.model.AppUserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRoleRepository extends JpaRepository<AppUserRole, AppUserRoleId> {


}
