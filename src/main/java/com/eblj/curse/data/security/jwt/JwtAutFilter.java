package com.eblj.curse.data.security.jwt;

import com.eblj.curse.data.service.UsuarioService;
import com.eblj.curse.data.service.impl.UsuarioServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAutFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UsuarioServiceImpl usuarioServiceImpl;

    public JwtAutFilter(JwtService jwtService, UsuarioServiceImpl usuarioServiceImpl) {
        this.jwtService = jwtService;
        this.usuarioServiceImpl = usuarioServiceImpl;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

       String autthorization = request.getHeader("Authorization");

       if(autthorization!=null && autthorization.startsWith("Bearer")){
          String token = autthorization.split(" ")[1];
          boolean isValid =  jwtService.tokenValido(token);
          if(isValid){
              String loginUsuario = jwtService.obterLoginUsuario(token);
              UserDetails usuario= usuarioServiceImpl.loadUserByUsername(loginUsuario);
              UsernamePasswordAuthenticationToken user =
                      new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
              user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              SecurityContextHolder.getContext().setAuthentication(user);

          }
       }
       filterChain.doFilter(request,response);
    }

}
