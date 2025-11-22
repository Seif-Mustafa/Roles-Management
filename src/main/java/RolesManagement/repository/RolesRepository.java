package RolesManagement.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import RolesManagement.dto.response.RoleButtonsResponse;
import RolesManagement.dto.response.RoleDetailsResponse;
import RolesManagement.dto.response.RolePagesResponse;
import RolesManagement.dto.response.RoleUsersResponse;
import RolesManagement.model.AppRole;

@Repository
public interface RolesRepository extends JpaRepository<AppRole, Long> {

    @Query("""
            SELECT new RolesManagement.dto.response.RoleUsersResponse$UserResponse(au.userId, au.appUsername, au.isActive)
            FROM AppUser au
            JOIN AppUserRole aur ON aur.id.roleId = :roleId AND au.userId = aur.id.userId 
            """)
    List<RoleUsersResponse.UserResponse> getRoleUsers(@Param("roleId") Long roleId);

    @Query("""
            SELECT new RolesManagement.dto.response.RolePagesResponse$PageResponse(ap.pageId, ap.pageName, ap.resourceCode, ap.parentPageId, ap.isActive)
            FROM AppPage ap
            JOIN AppPageRole apr ON apr.id.roleId = :roleId AND ap.pageId = apr.id.pageId 
            """)
    List<RolePagesResponse.PageResponse> getRolePages(@Param("roleId") Long roleId);

    @Query("""
            SELECT new RolesManagement.dto.response.RoleButtonsResponse$ButtonResponse(ab.buttonId, ab.buttonName, ab.pageId, ab.isActive)
            FROM AppButton ab
            JOIN AppButtonRole abr ON abr.id.roleId = :roleId AND ab.buttonId = abr.id.buttonId
                        """)
    List<RoleButtonsResponse.ButtonResponse> getRoleButtons(@Param("roleId") Long roleId);

    @Query("""
            SELECT new RolesManagement.dto.response.RoleDetailsResponse$RoleDetailsPage(
            ap.pageId, ap.pageName, ap.isActive,
            CASE WHEN apr.id.roleId IS NOT NULL THEN 'Y' ELSE 'N' END)
            FROM AppPage ap
            LEFT JOIN AppPageRole apr ON ap.pageId = apr.id.pageId AND apr.id.roleId = :roleId
            WHERE ap.pageId NOT IN (SELECT DISTINCT ap2.parentPageId FROM AppPage ap2 WHERE ap2.parentPageId IS NOT NULL)
            """)
    List<RoleDetailsResponse.RoleDetailsPage> getAllPagesByRoleId(@Param("roleId") Long roleId);

    @Query("""
            SELECT new RolesManagement.dto.response.RoleDetailsResponse$RoleDetailsButton(
            ab.buttonId,
            CONCAT(ab.buttonName,' - ', ap.pageName), ab.isActive,
            CASE WHEN abr.id.roleId IS NOT NULL THEN 'Y' ELSE 'N' END)
            FROM AppButton ab
            LEFT JOIN AppButtonRole abr ON ab.buttonId = abr.id.buttonId AND abr.id.roleId = :roleId
            LEFT JOIN AppPage ap ON ap.pageId = ab.pageId
            """)
    List<RoleDetailsResponse.RoleDetailsButton> getAllButtonsByRoleId(@Param("roleId") Long roleId);

    @Query("""
            SELECT new RolesManagement.dto.response.RoleDetailsResponse$RoleDetailsUser(
            au.userId, au.appUsername, au.isActive,
            CASE WHEN aur.id.roleId IS NOT NULL THEN 'Y' ELSE 'N' END)
            FROM AppUser au
            LEFT JOIN AppUserRole aur ON au.userId = aur.id.userId AND aur.id.roleId = :roleId
            """)

    List<RoleDetailsResponse.RoleDetailsUser> getAllUsersByRoleId(@Param("roleId") Long roleId);

}
