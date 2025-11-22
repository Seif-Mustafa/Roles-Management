package RolesManagement.controller;

import RolesManagement.dto.generic.ApiResponse;
import RolesManagement.dto.request.CreatePageRoleRequest;
import RolesManagement.dto.request.DeletePageRoleRequest;
import RolesManagement.model.AppPageRole;
import RolesManagement.service.PageRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/page-role")
public class PageRoleController {

    @Autowired
    PageRoleService pageRoleService;

    @PostMapping
    public ResponseEntity<ApiResponse<AppPageRole>> createPageRole(@RequestBody CreatePageRoleRequest createRolePageRequest) {
        AppPageRole appPageRole = pageRoleService.createPageRole(createRolePageRequest);
        return ApiResponse.created(appPageRole, "Page Role Created Successfully");
    }


    @DeleteMapping
    public ResponseEntity<ApiResponse<AppPageRole>> deletePageRole(@RequestBody DeletePageRoleRequest deletePageRoleRequest) {
        AppPageRole appPageRole = pageRoleService.deletePageRole(deletePageRoleRequest);



        return ApiResponse.success(appPageRole, "Page Role Deleted Successfully");
    }

}
