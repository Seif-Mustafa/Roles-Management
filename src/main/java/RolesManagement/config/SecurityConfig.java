package RolesManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use BCrypt, which is a strong, modern hashing algorithm
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF
                .csrf(csrf -> csrf.disable())

                // 2. Define authorization rules
                .authorizeHttpRequests(auth -> auth

                        // 3. THIS IS THE CHANGE:
                        //    Permit all requests, regardless of endpoint
                        .anyRequest().permitAll()
                )

                // 4. Disable the default login forms
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // 1. Disable CSRF (common for stateless REST APIs)
//                .csrf(csrf -> csrf.disable())
//
//                // 2. Define authorization rules
//                .authorizeHttpRequests(auth -> auth
//
//                        // 3. Make your login and registration endpoints public
//                        //    !!! IMPORTANT: REPLACE WITH YOUR ACTUAL API PATHS !!!
////                        .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
//                        .requestMatchers("/*","/*/*").permitAll()
//
//                        // 4. Secure all other endpoints
//                        .anyRequest().authenticated())
//
//                // 5. Disable the default Spring Security login form and basic auth
//                //    You are building your own login logic.
//                .formLogin(form -> form.disable()).httpBasic(basic -> basic.disable());
//
//        return http.build();
//    }
}
