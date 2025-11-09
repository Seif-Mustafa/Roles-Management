package RolesManagement.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="app_button")
public class AppButton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="button_id")
    private Long buttonId;

    @Column(name="button_name")
    private String buttonName;

    @Column(name="page_id")
    private Long pageId;

    @Column(name="is_active")
    private char isActive;
}
