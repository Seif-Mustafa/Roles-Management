package RolesManagement.repository;

import RolesManagement.dto.response.UserRolesResponse;
import RolesManagement.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
//new RolesManagement.dto.response.UserRolesResponse.AppRoleResponse(ar.roleId, ar.roleName, ar.description)
//    @Query("""
//            SELECT ar
//            FROM AppUserRole aur
//            JOIN AppRole ar ON aur.id.roleId = ar.roleId
//            WHERE aur.id.userId = :userId
//            """)
//    List<AppRole> getUserRoles(@Param("userId") Long userId);


    @Query("""
                SELECT new RolesManagement.dto.response.UserRolesResponse$AppRoleResponse(
                    ar.roleId, ar.roleName, ar.description
                )
                FROM AppUserRole aur
                JOIN AppRole ar ON aur.id.roleId = ar.roleId
                WHERE aur.id.userId = :userId
            """)
    List<UserRolesResponse.AppRoleResponse> getUserRoles(@Param("userId") Long userId);
}
