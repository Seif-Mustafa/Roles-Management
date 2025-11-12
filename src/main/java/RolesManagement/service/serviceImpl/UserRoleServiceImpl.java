package RolesManagement.service.serviceImpl;

import RolesManagement.dto.request.CreateUserRoleRequest;
import RolesManagement.dto.request.DeleteUserRoleRequest;
import RolesManagement.mapper.UserRoleMapper;
import RolesManagement.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RolesManagement.model.AppUserRole;
import RolesManagement.repository.UserRoleRepository;
import RolesManagement.repository.UserRepository;
import RolesManagement.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleRepository appUserRoleRepository;
    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RolesRepository roleRepository;

    @Override
    public AppUserRole createUserRole(CreateUserRoleRequest createUserRoleRequest) {
        userRepository.findById(createUserRoleRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        roleRepository.findById(createUserRoleRequest.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        AppUserRole appUserRole = appUserRoleRepository.save(userRoleMapper.toEntity(createUserRoleRequest));
        return appUserRole;
    }

    @Override
    public AppUserRole deleteUserRole(DeleteUserRoleRequest deleteUserRoleRequest) {
        AppUserRole appUserRole = appUserRoleRepository.findById(userRoleMapper.toEntity(deleteUserRoleRequest)).orElseThrow(()->new RuntimeException("Record not found"));
        appUserRoleRepository.delete(appUserRole);
        return appUserRole;
    }


}
