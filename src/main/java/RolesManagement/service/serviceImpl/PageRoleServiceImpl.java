package RolesManagement.service.serviceImpl;

import RolesManagement.dto.request.CreatePageRoleRequest;
import RolesManagement.dto.request.DeletePageRoleRequest;
import RolesManagement.mapper.PageRoleMapper;
import RolesManagement.model.AppPageRole;
import RolesManagement.repository.PageRepository;
import RolesManagement.repository.PageRoleRepository;
import RolesManagement.repository.RolesRepository;
import RolesManagement.service.PageRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageRoleServiceImpl implements PageRoleService {

    @Autowired
    PageRoleRepository pageRoleRepository;

    @Autowired
    PageRoleMapper pageRoleMapper;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    PageRepository pageRepository;

    @Override
    public AppPageRole createPageRole(CreatePageRoleRequest createRolePageRequest) {
        pageRepository.findById(createRolePageRequest.getPageId())
                .orElseThrow(() -> new RuntimeException("Page not found"));
        rolesRepository.findById(createRolePageRequest.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        AppPageRole appPageRole = pageRoleRepository.save(pageRoleMapper.toEntity(createRolePageRequest));
        return appPageRole;
    }


    @Override
    public AppPageRole deletePageRole(DeletePageRoleRequest deletePageRoleRequest) {
        AppPageRole appPageRole = pageRoleRepository.findById(pageRoleMapper.toEntity(deletePageRoleRequest)).orElseThrow(() -> new RuntimeException("Page Role not found"));
        pageRoleRepository.delete(appPageRole);
        return appPageRole;
    }
}
