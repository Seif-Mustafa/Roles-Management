package RolesManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "app_username", unique = true, nullable = false)
    private String appUsername;

    @JsonIgnore
    @Column(name = "app_password")
    private String appPassword;

    @Column(name = "email")
    private String email;

    @Column(name = "is_active")
    private char isActive;

    @Column(name = "created_by")
    private Long createdBy;

    @CreationTimestamp
    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "modified_by")
    private Long modifiedBy;

    @UpdateTimestamp
    @Column(name = "modified_on")
    private Timestamp modifiedOn;


//    @Column(name="action_by")
//    private Long actionBy;
//
//    @Column(name="action_on")
//    private Timestamp actionOn;
}
