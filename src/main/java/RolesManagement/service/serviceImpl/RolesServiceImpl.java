package RolesManagement.service.serviceImpl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import RolesManagement.dto.request.UpdateRoleDetailsRequest;
import RolesManagement.dto.response.RoleButtonsResponse;
import RolesManagement.dto.response.RoleDetailsResponse;
import RolesManagement.model.*;
import RolesManagement.repository.ButtonRoleRepository;
import RolesManagement.repository.PageRoleRepository;
import RolesManagement.repository.UserRoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import RolesManagement.dto.request.CreateRoleRequest;
import RolesManagement.dto.response.RolePagesResponse;
import RolesManagement.dto.response.RoleUsersResponse;
import RolesManagement.mapper.RoleMapper;
import RolesManagement.repository.RolesRepository;
import RolesManagement.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private final RolesRepository rolesRepository;

    @Autowired
    private final RoleMapper roleMapper;

    @Autowired
    private final PageRoleRepository pageRoleRepository;

    @Autowired
    private final ButtonRoleRepository buttonRoleRepository;

    @Autowired
    private final UserRoleRepository userRoleRepository;

    public RolesServiceImpl(RolesRepository rolesRepository,
                            RoleMapper roleMapper,
                            PageRoleRepository pageRoleRepository,
                            ButtonRoleRepository buttonRoleRepository,
                            UserRoleRepository userRoleRepository) {
        this.rolesRepository = rolesRepository;
        this.roleMapper = roleMapper;
        this.pageRoleRepository = pageRoleRepository;
        this.buttonRoleRepository = buttonRoleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public AppRole createRole(CreateRoleRequest createRoleRequest) {
        AppRole appRole = rolesRepository.save(roleMapper.toEntity(createRoleRequest));
        return appRole;
    }

    @Override
    public AppRole getRoleById(Long roleId) {
        AppRole appRole = rolesRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        return appRole;
    }

    @Override
    public AppRole updateRole(Long roleId, CreateRoleRequest createRoleRequest) {
        AppRole appRole = rolesRepository.save(roleMapper.toEntity(roleId, createRoleRequest));
        return appRole;
    }

    @Override
    public AppRole deleteRole(Long roleId) {
        AppRole appRole = getRoleById(roleId);

        rolesRepository.delete(appRole);
        return appRole;
    }

    @Override
    public List<AppRole> getAllRoles() {
        return rolesRepository.findAll();
    }

    @Override
    public RoleUsersResponse getRoleUsers(Long roleId) {
        AppRole appRole = getRoleById(roleId);

        List<RoleUsersResponse.UserResponse> roleUsers = rolesRepository.getRoleUsers(roleId);

        return RoleUsersResponse.builder().roleId(appRole.getRoleId())
                .roleName(appRole.getRoleName())
                .description(appRole.getDescription())
                .users(roleUsers)
                .build();
    }


    @Override
    public RolePagesResponse getRolePages(Long roleId) {
        AppRole appRole = getRoleById(roleId);

        List<RolePagesResponse.PageResponse> rolePages = rolesRepository.getRolePages(roleId);

        return RolePagesResponse.builder().roleId(appRole.getRoleId())
                .roleName(appRole.getRoleName())
                .description(appRole.getDescription())
                .pages(rolePages)
                .build();
    }

    @Override
    public RoleButtonsResponse getRoleButtons(Long roleId) {
        AppRole appRole = getRoleById(roleId);

        List<RoleButtonsResponse.ButtonResponse> roleButtons = rolesRepository.getRoleButtons(roleId);

        return RoleButtonsResponse.builder()
                .roleId(appRole.getRoleId())
                .roleName(appRole.getRoleName())
                .description(appRole.getDescription())
                .buttons(roleButtons)
                .build();
    }

    @Override
    public RoleDetailsResponse getRoleDetails(Long roleId) {
        AppRole appRole = getRoleById(roleId);

        List<RoleDetailsResponse.RoleDetailsPage> pages = rolesRepository.getAllPagesByRoleId(roleId);
        List<RoleDetailsResponse.RoleDetailsButton> buttons = rolesRepository.getAllButtonsByRoleId(roleId);
        List<RoleDetailsResponse.RoleDetailsUser> users = rolesRepository.getAllUsersByRoleId(roleId);
        return roleMapper.toDto(appRole, pages, buttons, users);
    }

    @Override
    @Transactional
    public RoleDetailsResponse saveRoleDetails(Long roleId, UpdateRoleDetailsRequest updateRoleDetailsRequest) {
        AppRole appRole = getRoleById(roleId); // Validating that role exists
        // Saving role
        rolesRepository.save(roleMapper.toEntity(roleId, updateRoleDetailsRequest));

        // Saving Pages
        pageRoleRepository.deleteAllByRoleId(roleId);
        List<AppPageRole> selectedPages = new ArrayList<>();

        updateRoleDetailsRequest.getPages().forEach(
                (page) -> {
                    if (page.getIsSelected() == 'Y') {
                        AppPageRoleId id = new AppPageRoleId(page.getPageId(),roleId);
                        AppPageRole apr = new AppPageRole();
                        apr.setId(id);
                        apr.setActionBy(updateRoleDetailsRequest.getModifiedBy());
                        selectedPages.add(apr);
                    }
                }
        );
        pageRoleRepository.saveAll(selectedPages);

        // Saving Buttons
        buttonRoleRepository.deleteAllByRoleId(roleId);
        List<AppButtonRole> selectedButtons = new ArrayList<>();
        updateRoleDetailsRequest.getButtons().forEach(
                (button) -> {
                    if (button.getIsSelected() == 'Y') {
                        AppButtonRoleId id = new AppButtonRoleId (button.getButtonId(),roleId);
                        AppButtonRole abr = new AppButtonRole();
                        abr.setId(id);
                        abr.setActionBy(updateRoleDetailsRequest.getModifiedBy());
                        selectedButtons.add(abr);
                    }
                }
        );
        buttonRoleRepository.saveAll(selectedButtons);

        // Saving Users
        userRoleRepository.deleteAllByRoleId(roleId);
        List<AppUserRole> selectedUsers = new ArrayList<>();

        updateRoleDetailsRequest.getUsers().forEach(
                (user) -> {
                    if (user.getIsSelected() == 'Y') {
                        AppUserRoleId id = new AppUserRoleId( user.getUserId(),roleId);

                        AppUserRole aur = new AppUserRole();
                        aur.setId(id);
                        aur.setActionBy(updateRoleDetailsRequest.getModifiedBy());
                        selectedUsers.add(aur);
                    }
                }
        );

        userRoleRepository.saveAll(selectedUsers);

        return getRoleDetails(roleId);
    }

}
