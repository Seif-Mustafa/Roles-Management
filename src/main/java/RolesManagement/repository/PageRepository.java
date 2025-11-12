package RolesManagement.repository;

import RolesManagement.model.AppPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<AppPage,Long> {
}
