package RolesManagement.repository;


import RolesManagement.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<AppRole,Long> {
}
