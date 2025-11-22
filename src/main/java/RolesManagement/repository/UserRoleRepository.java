package RolesManagement.repository;

import RolesManagement.model.AppUserRole;
import RolesManagement.model.AppUserRoleId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<AppUserRole, AppUserRoleId> {

    @Modifying
    @Transactional
    @Query("DELETE FROM AppUserRole aur WHERE aur.id.roleId = :roleId")
    void deleteAllByRoleId(@Param("roleId") Long roleId);
}
