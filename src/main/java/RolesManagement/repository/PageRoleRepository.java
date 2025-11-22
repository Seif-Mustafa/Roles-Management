package RolesManagement.repository;

import RolesManagement.model.AppPageRole;
import RolesManagement.model.AppPageRoleId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRoleRepository extends JpaRepository<AppPageRole, AppPageRoleId> {

    @Modifying
    @Transactional
    @Query("DELETE FROM AppPageRole apr WHERE apr.id.roleId = :roleId")
    void deleteAllByRoleId(@Param("roleId") Long roleId);
}
