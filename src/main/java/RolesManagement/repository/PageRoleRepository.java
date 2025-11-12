package RolesManagement.repository;

import RolesManagement.model.AppPageRole;
import RolesManagement.model.AppPageRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRoleRepository extends JpaRepository<AppPageRole, AppPageRoleId> {
}
