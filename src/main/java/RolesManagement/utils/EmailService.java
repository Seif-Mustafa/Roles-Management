package RolesManagement.utils;

import RolesManagement.dto.request.CreateUserRequest;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;

    @Autowired
    EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }


    /**
     * Sends an HTML email.
     * This @Async annotation runs the method in a separate thread,
     * so the calling method (e.g., registerUser) doesn't wait.
     */
    @Async
    public void sendHtmlEmail(String to, String subject, String htmlBody) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true); // true = indicates this is HTML
            // helper.setFrom("no-reply@yourdomain.com"); // Uncomment and set your "from" address

            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            // Log the exception (e.g., using slf4j)
            // logger.error("Failed to send email to {}: {}", to, e.getMessage());
            // It's usually not good to throw the exception here,
            // as it would crash the async thread. Just log it.
        }
    }

    public void createUserMailSending(CreateUserRequest createUserRequest, String userPassword) {
        Context context = new Context();
        context.setVariable("username", createUserRequest.getAppUsername());
        context.setVariable("password", userPassword);
        String htmlBody = templateEngine.process("emails/welcome", context);

       sendHtmlEmail(createUserRequest.getEmail(),
                "Welcome to Roles Management!",
                htmlBody
        );
    }

    public void forgetPasswordMailSending(String emailTo, String username, String password) {
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("password", password);
        String htmlBody = templateEngine.process("emails/change-password", context);

        sendHtmlEmail(emailTo,
                "Password Changed",
                htmlBody
        );
    }
}
