package br.com.emprestimos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(errors -> errors
                .authenticationEntryPoint((request, response, exception) -> {
                    response.setStatus(401);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"message\":\"Não autenticado\"}");
                })
                .accessDeniedHandler((request, response, exception) -> {
                    response.setStatus(403);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"message\":\"Acesso negado\"}");
                }))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index.html", "/*.css", "/*.js", "/auth/login").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/equipamentos", "/colaboradores", "/emprestimos").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/equipamentos/**", "/emprestimos/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/equipamentos/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/colaboradores", "/colaboradores/{id}").hasRole("ADMIN")
                .anyRequest().authenticated())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
