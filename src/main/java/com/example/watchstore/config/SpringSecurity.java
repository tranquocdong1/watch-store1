package com.example.watchstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Vô hiệu hóa CSRF
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/register/**").permitAll() // Cho phép truy cập không cần đăng nhập
                                .requestMatchers("/images/**").permitAll()
                                .requestMatchers("/index").permitAll()
                                .requestMatchers("/products").permitAll()
                                .requestMatchers("/admin/products").hasRole("ADMIN") // Chỉ ADMIN mới được truy cập
                                .requestMatchers("/users").hasRole("ADMIN") // Chỉ cho phép ADMIN truy cập
                                .anyRequest().permitAll()
                )
                .formLogin(form ->
                        form
                                .loginPage("/login") // Trang đăng nhập tùy chỉnh
                                .loginProcessingUrl("/login")
                                .successHandler(authenticationSuccessHandler()) // Use the custom success handler
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // URL logout
                                .permitAll()
                );

        return http.build();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    // Custom success handler to redirect based on role
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
            if (isAdmin) {
                response.sendRedirect("/admin/products"); // Redirect to /admin for admin users
            } else {
                response.sendRedirect("/"); // Redirect to /index for other users
            }
        };
    }
}
