package github.tourism.config.security;

import github.tourism.web.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers(AbstractHttpConfigurer::disable) // AuthenticationConfigurer::disable 제거
                .formLogin(AbstractHttpConfigurer::disable) // formLogin 비활성화
                .csrf(AbstractHttpConfigurer::disable) // CSRF 비활성화
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 설정
                .httpBasic(AbstractHttpConfigurer::disable) // HTTP Basic 비활성화
                .rememberMe(AbstractHttpConfigurer::disable) // Remember Me 비활성화
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/resources/static/**","/auth/login","/auth/signup", "/auth/email",
                                "/books","/books/{id}","/books/category/{category}"
                                ,"/v3/api-docs/**", "/swagger-ui/**","/goods/**","/maps/**"
                        ).permitAll()
                        .requestMatchers("/auth/secession", "/cart/**","/orders/**" ,"/api/mypage/**", "/payment/**").hasAuthority("ROLE_USER")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                        .accessDeniedHandler(new CustomerAccessDeniedHandler())
                )

                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
                "https://bookshoppingmall.vercel.app"// 프론트 배포 주소
        ));
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader("Bearer_Token");
        configuration.addAllowedHeader("*");
        configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE"));
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
