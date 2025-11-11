package RolesManagement.service.serviceImpl;

import RolesManagement.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RolesManagement.dto.response.UserRolesResponse;
import RolesManagement.model.AppUser;
import RolesManagement.model.AppUserRole;
import RolesManagement.repository.AppUserRoleRepository;
import RolesManagement.repository.UserRepository;
import RolesManagement.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    AppUserRoleRepository appUserRoleRepository;
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


}
