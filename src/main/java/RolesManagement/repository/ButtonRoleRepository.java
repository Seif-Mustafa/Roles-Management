package RolesManagement.repository;

import RolesManagement.model.AppButtonRole;
import RolesManagement.model.AppButtonRoleId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ButtonRoleRepository extends JpaRepository<AppButtonRole, AppButtonRoleId> {

    @Modifying
    @Transactional
    @Query("DELETE FROM AppButtonRole abr WHERE abr.id.roleId = :roleId")
    void deleteAllByRoleId(@Param("roleId") Long roleId);

}
