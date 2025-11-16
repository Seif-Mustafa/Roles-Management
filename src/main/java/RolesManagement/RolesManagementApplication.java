package RolesManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync // For the Email sending
public class RolesManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(RolesManagementApplication.class, args);
	}
}
