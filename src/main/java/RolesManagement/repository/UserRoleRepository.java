package RolesManagement.repository;

import RolesManagement.model.AppUserRole;
import RolesManagement.model.AppUserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<AppUserRole, AppUserRoleId> {

}
