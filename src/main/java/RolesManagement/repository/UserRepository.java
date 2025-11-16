package RolesManagement.repository;

import RolesManagement.dto.response.UserButtonsResponse;
import RolesManagement.dto.response.UserPagesResponse;
import RolesManagement.dto.response.UserRolesResponse;
import RolesManagement.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    List<AppUser> findByIsActive(char status);

    Optional<AppUser> findByAppUsername(String appUsername);

    Optional<AppUser> findByAppUsernameAndEmail(String appUsername, String email);

    @Query("""
                SELECT new RolesManagement.dto.response.UserRolesResponse$AppRoleResponse(
                    ar.roleId, ar.roleName, ar.description, ar.isActive
                )
                FROM AppUserRole aur
                JOIN AppRole ar ON aur.id.roleId = ar.roleId
                WHERE aur.id.userId = :userId
            """)
    List<UserRolesResponse.AppRoleResponse> getUserRoles(@Param("userId") Long userId);

    @Query("""
            SELECT DISTINCT new RolesManagement.dto.response.UserPagesResponse$PageResponse(ap.pageId, ap.pageName, ap.isActive)
            FROM AppPage ap
            JOIN AppPageRole apr ON ap.pageId = apr.id.pageId 
            JOIN AppUserRole aur ON aur.id.roleId = apr.id.roleId AND aur.id.userId = :userId
            """)
    List<UserPagesResponse.PageResponse> getUserPages(@Param("userId") Long userId);

//    SELECT ab.button_id, ab.button_name, ab.page_id, ab.is_active
//    FROM app_button ab
//    JOIN app_button_role abr ON ab.button_id = abr.button_id
//    JOIN app_user_role aur ON aur.user_id = 1 AND abr.role_id = aur.role_id
    @Query("""
            SELECT DISTINCT new RolesManagement.dto.response.UserButtonsResponse$ButtonResponse(ab.buttonId, ab.buttonName, ab.pageId, ab.isActive)
            FROM AppButton ab
            JOIN AppButtonRole abr ON ab.buttonId = abr.id.buttonId
            JOIN AppUserRole aur ON aur.id.userId = :userId AND abr.id.roleId = aur.id.roleId
            """)
    List<UserButtonsResponse.ButtonResponse> getUserButtons(@Param("userId") Long userId);




}
