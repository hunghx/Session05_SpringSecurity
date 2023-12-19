package ra.springsecurity.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ra.springsecurity.security.jwt.JwtAuthTokenFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true) // cho phép cấu hình phân quyền theo phương thức
@RequiredArgsConstructor
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    public final JwtAuthTokenFilter jwtAuthTokenFilter;
    @Bean
    AuthenticationEntryPoint authenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    // tạo bộ lọc phân quyền
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable())
                .exceptionHandling(cus->cus.authenticationEntryPoint(authenticationEntryPoint()))
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(cus->cus.requestMatchers("/api/v1/public/**").permitAll() // công khai
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/pm/**").hasAnyRole("ADMIN","PM")
                        .requestMatchers("/api/v1/user/**").hasAnyRole("ADMIN","PM","USER")
                );
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
     return  http.build();
    }
}
