package RolesManagement.service.serviceImpl;

import RolesManagement.dto.request.CreateRoleRequest;
import RolesManagement.mapper.RoleMapper;
import RolesManagement.model.AppRole;
import RolesManagement.repository.RolesRepository;
import RolesManagement.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public AppRole getRole(Long roleId){
        Optional<AppRole> appRole = rolesRepository.findById(roleId);
        if(appRole.isPresent()){
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
    public AppRole deleteRole(Long roleId){
        Optional<AppRole> appRole = rolesRepository.findById(roleId);
        if(appRole.isPresent()){
            rolesRepository.delete(appRole.get());
            return appRole.get();
        }
        return null;
    }

}
