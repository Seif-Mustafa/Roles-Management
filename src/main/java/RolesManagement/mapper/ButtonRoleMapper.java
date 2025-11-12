package RolesManagement.mapper;

import RolesManagement.dto.request.CreateButtonRoleRequest;
import RolesManagement.dto.request.DeleteButtonRoleRequest;
import RolesManagement.model.AppButtonRole;
import RolesManagement.model.AppButtonRoleId;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class ButtonRoleMapper {

    public AppButtonRole toEntity(CreateButtonRoleRequest createButtonRoleRequest) {
        AppButtonRoleId appButtonRoleId = new AppButtonRoleId();
        appButtonRoleId.setButtonId(createButtonRoleRequest.getButtonId());
        appButtonRoleId.setRoleId(createButtonRoleRequest.getRoleId());
        AppButtonRole appButtonRole = new AppButtonRole();
        appButtonRole.setId(appButtonRoleId);
        appButtonRole.setActionBy(createButtonRoleRequest.getActionBy());
        appButtonRole.setActionOn(Timestamp.from(Instant.now()));
        return appButtonRole;
    }

    public AppButtonRoleId toEntity(DeleteButtonRoleRequest deleteButtonRoleRequest){
        AppButtonRoleId appButtonRoleId = new AppButtonRoleId();
        appButtonRoleId.setRoleId(deleteButtonRoleRequest.getRoleId());
        appButtonRoleId.setButtonId(deleteButtonRoleRequest.getButtonId());
        return appButtonRoleId;
    }


}
