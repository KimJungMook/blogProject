package com.jungmook.blog.config;

import com.jungmook.blog.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                // cors 정책(현재는 Application에서 작업을 해뒀으므로 기본 설정 사용)
                .cors(Customizer.withDefaults())
                // csrf 대책(현재는 CSRF에 대한 대책을 비활성화)
                .csrf((csrf) -> csrf.disable())
                // Basic 인증 (현재는 Bearer token 인증 방법을 사용하기 때문에 비활성화)
                .httpBasic((httpBasic) -> httpBasic.disable())
                // 세션 기반 인증 (현재는 Session 기반 인증을 사용하지 않기 때문에 상태를 없앰)
                .sessionManagement((sessionManageMent) -> sessionManageMent.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorizeHttpRequest) -> authorizeHttpRequest.requestMatchers("/", "/api/auth/**")
                        .permitAll().anyRequest().authenticated());

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
