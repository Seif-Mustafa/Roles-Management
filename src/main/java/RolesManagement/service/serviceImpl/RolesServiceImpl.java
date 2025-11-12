package RolesManagement.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RolesManagement.dto.request.CreateRoleRequest;
import RolesManagement.dto.response.RolePagesResponse;
import RolesManagement.dto.response.RoleUsersResponse;
import RolesManagement.mapper.RoleMapper;
import RolesManagement.model.AppRole;
import RolesManagement.repository.RolesRepository;
import RolesManagement.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public AppRole createRole(CreateRoleRequest createRoleRequest) {
        AppRole appRole = rolesRepository.save(roleMapper.toEntity(createRoleRequest));
        return appRole;
    }

    @Override
    public AppRole getRole(Long roleId) {
        Optional<AppRole> appRole = rolesRepository.findById(roleId);
        if (appRole.isPresent()) {
            return appRole.get();
        }
        return null;
    }

    @Override
    public AppRole updateRole(Long roleId, CreateRoleRequest createRoleRequest) {
        AppRole appRole = rolesRepository.save(roleMapper.toEntity(roleId, createRoleRequest));
        return appRole;
    }

    @Override
    public AppRole deleteRole(Long roleId) {
        Optional<AppRole> appRole = rolesRepository.findById(roleId);
        if (appRole.isPresent()) {
            rolesRepository.delete(appRole.get());
            return appRole.get();
        }
        return null;
    }

    @Override
    public List<AppRole> getAllRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public RoleUsersResponse getRoleUsers(Long roleId) {
        AppRole appRole = rolesRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));

        List<RoleUsersResponse.UserResponse> roleUsers = rolesRepository.getRoleUsers(roleId);

        return RoleUsersResponse.builder().roleId(appRole.getRoleId())
                .roleName(appRole.getRoleName())
                .description(appRole.getDescription())
                .users(roleUsers)
                .build();
    }


    @Override
    public RolePagesResponse getRolePages(Long roleId) {
        AppRole appRole = rolesRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));

        List<RolePagesResponse.PageResponse> rolePages = rolesRepository.getRolePages(roleId);

        RolePagesResponse rolePagesResponse = new RolePagesResponse();

        return rolePagesResponse.builder().roleId(appRole.getRoleId())
                .roleName(appRole.getRoleName())
                .description(appRole.getDescription())
                .pages(rolePages)
                .build();
    }

}
