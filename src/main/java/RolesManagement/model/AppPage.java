package RolesManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_page")
public class AppPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_id")
    private Long pageId;

    @Column(name = "page_name")
    private String pageName;

    @Column(name = "resource_code", unique = true, nullable = false)
    private String resourceCode;

    @Column(name = "parent_page_id", nullable = true)
    private Long parentPageId;
    
    @Column(name = "is_active")
    private char isActive;

}
