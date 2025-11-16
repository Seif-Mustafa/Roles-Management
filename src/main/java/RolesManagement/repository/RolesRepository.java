package RolesManagement.repository;


import RolesManagement.dto.response.RoleButtonsResponse;
import RolesManagement.dto.response.RolePagesResponse;
import RolesManagement.dto.response.RoleUsersResponse;
import RolesManagement.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<AppRole, Long> {

    @Query("""
            SELECT new RolesManagement.dto.response.RoleUsersResponse$UserResponse(au.userId, au.appUsername, au.isActive)
            FROM AppUser au
            JOIN AppUserRole aur ON aur.id.roleId = :roleId AND au.userId = aur.id.userId 
            """)
    List<RoleUsersResponse.UserResponse> getRoleUsers(@Param("roleId") Long roleId);

    @Query("""
            SELECT new RolesManagement.dto.response.RolePagesResponse$PageResponse(ap.pageId, ap.pageName, ap.isActive)
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

}
