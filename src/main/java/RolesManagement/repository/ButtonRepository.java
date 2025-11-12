package RolesManagement.repository;

import RolesManagement.model.AppButton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ButtonRepository extends JpaRepository<AppButton, Long> {
}
