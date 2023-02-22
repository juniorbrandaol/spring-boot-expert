package com.eblj.curse.data.domain.config;

import com.eblj.curse.data.security.jwt.JwtAutFilter;
import com.eblj.curse.data.security.jwt.JwtService;
import com.eblj.curse.data.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;


@Configuration
public class SecurityConfig  {
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;
    @Autowired
    private JwtService jwtService;
    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }
    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAutFilter(jwtService,usuarioServiceImpl);
    }
    @Bean
    public UserDetailsService userDetailsService(AuthenticationManagerBuilder aut) throws Exception {
        aut
                .userDetailsService(usuarioServiceImpl)
                .passwordEncoder(passwordEncoder());
         return aut.getDefaultUserDetailsService();
        }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
                //se não vai usar uma aplicação web, apenas rest
                .csrf().disable()
                .authorizeHttpRequests()
                   .requestMatchers("/api/clientes/**").hasRole("USER")
                   .requestMatchers("/api/produto/**").hasRole("ADMIN")
                   .requestMatchers("/api/pedido/**").hasRole("USER")
                   .requestMatchers("/api/cartao/**").hasRole("USER")
                   .requestMatchers("/api/categoria/**").hasRole("USER")
                   .requestMatchers("/api/endereco/**").hasRole("USER")
                   .requestMatchers("/api/estoque/**").hasRole("ADMIN")
                   .requestMatchers("/api/fornecedor/**").hasRole("USER")
                   .requestMatchers("/api/marca/**").hasRole("USER")
                   .requestMatchers("/api/cidade/**").hasRole("USER")
                   .requestMatchers(HttpMethod.POST,"/api/usuarios/**").permitAll()
                   .anyRequest().authenticated()//garante que se esquecer de mapear outra api, o minimo de autenticação
                .and()
                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.httpBasic();
                //.formLogin();
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }



}