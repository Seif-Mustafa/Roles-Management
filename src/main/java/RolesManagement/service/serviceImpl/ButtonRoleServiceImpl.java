package RolesManagement.service.serviceImpl;

import RolesManagement.dto.request.CreateButtonRoleRequest;
import RolesManagement.dto.request.DeleteButtonRoleRequest;
import RolesManagement.mapper.ButtonRoleMapper;
import RolesManagement.model.AppButtonRole;
import RolesManagement.repository.ButtonRepository;
import RolesManagement.repository.ButtonRoleRepository;
import RolesManagement.repository.RolesRepository;
import RolesManagement.service.ButtonRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ButtonRoleServiceImpl implements ButtonRoleService {

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    ButtonRepository buttonRepository;

    @Autowired
    ButtonRoleRepository buttonRoleRepository;


    @Autowired
    ButtonRoleMapper buttonRoleMapper;

    @Override
    public AppButtonRole createButtonRole(CreateButtonRoleRequest createButtonRoleRequest) {
        rolesRepository.findById(createButtonRoleRequest.getRoleId()).orElseThrow(()-> new RuntimeException("Role not found"));
        buttonRepository.findById(createButtonRoleRequest.getButtonId()).orElseThrow(()-> new RuntimeException("Button not found"));
        AppButtonRole appButtonRole =  buttonRoleRepository.save(buttonRoleMapper.toEntity(createButtonRoleRequest));
        return appButtonRole;
    }

    @Override
    public AppButtonRole deleteButtonRole(DeleteButtonRoleRequest deleteButtonRoleRequest){
        AppButtonRole appButtonRole = buttonRoleRepository.findById(buttonRoleMapper.toEntity(deleteButtonRoleRequest)).orElseThrow(()-> new RuntimeException("Button Role not found"));
        buttonRoleRepository.delete(appButtonRole);
        return appButtonRole;
    }

}
