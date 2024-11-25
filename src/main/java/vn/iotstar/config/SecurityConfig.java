package vn.iotstar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Tắt CSRF (nếu không cần thiết)
            .authorizeRequests()
            .anyRequest().permitAll() // Cho phép tất cả các request mà không cần xác thực
            .and()
            .formLogin().disable() // Tắt trang login mặc định
            .httpBasic().disable(); // Tắt xác thực HTTP Basic

        return http.build();
    }
}
