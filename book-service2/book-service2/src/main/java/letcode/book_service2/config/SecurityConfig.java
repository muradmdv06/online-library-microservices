package letcode.book_service2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/books/**",
                                "/authors/**",
                                "/categories/**",
                                "/borrow-records/**"
                        ).permitAll()


                        .anyRequest().authenticated()
                )


                .httpBasic(httpBasic -> {});

        return http.build();
    }
}