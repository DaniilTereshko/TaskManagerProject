package org.taskmanager.task_server.endpoints.web.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.taskmanager.task_server.endpoints.web.util.JwtHandler;
import org.taskmanager.task_server.service.api.user.IUserService;
import org.taskmanager.user_client.core.dto.base.UserDTO;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtHandler jwtHandler;

    private final IUserService userService;

    public JwtFilter(JwtHandler jwtHandler, IUserService userService) {
        this.jwtHandler = jwtHandler;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse resp,
                                    FilterChain chain) throws ServletException, IOException {
        final String header = req.getHeader(HttpHeaders.AUTHORIZATION);

        if(isEmpty(header) || !header.startsWith("Bearer ")){
            chain.doFilter(req, resp);
            return;
        }

        String jwt = header.substring(7);
        if(!this.jwtHandler.validate(jwt)){
            chain.doFilter(req, resp);
            return;
        }

        UsernamePasswordAuthenticationToken authentication;
        String systemRole = this.jwtHandler.getSystemRole(jwt);
        if(systemRole != null){
            UserDetails system = User.builder()
                    .username(this.jwtHandler.getEmail(jwt))
                    .password("")
                    .authorities(new SimpleGrantedAuthority(systemRole))
                    .build();
            authentication = new UsernamePasswordAuthenticationToken(system, null, Collections.singleton(new SimpleGrantedAuthority(systemRole)));
        } else {
            UserDTO dto = this.userService.getMe(jwt);
            authentication =
                    new UsernamePasswordAuthenticationToken(dto, null,
                            dto.getRole() == null ? List.of() : List.of(new SimpleGrantedAuthority("ROLE_" + dto.getRole().name())));
        }

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, resp);
    }
}
