package org.taskmanager.user_server.endpoints.web.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.taskmanager.user_client.core.dto.base.UserDetailsDTO;
import org.taskmanager.user_server.dao.entity.User;
import org.taskmanager.user_server.endpoints.web.util.JwtHandler;
import org.taskmanager.user_server.service.api.user.IUserService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtHandler jwtHandler;
    private final IUserService userService;
    private final ConversionService conversionService;

    public JwtFilter(JwtHandler jwtHandler, IUserService userService, ConversionService conversionService) {
        this.jwtHandler = jwtHandler;
        this.userService = userService;
        this.conversionService = conversionService;
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
            UserDetails system = org.springframework.security.core.userdetails.User.builder()
                    .username(this.jwtHandler.getEmail(jwt))
                    .password("")
                    .authorities(new SimpleGrantedAuthority(systemRole))
                    .build();
            authentication = new UsernamePasswordAuthenticationToken(system, null, Collections.singleton(new SimpleGrantedAuthority(systemRole)));
        } else {
            User user = this.userService.findByEmail(this.jwtHandler.getEmail(jwt)).get();
            UserDetailsDTO userDetails = this.conversionService.convert(user, UserDetailsDTO.class);
            authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities() == null ? List.of() : userDetails.getAuthorities().stream().map(a -> new SimpleGrantedAuthority("ROLE_" + a.getAuthority())).toList());
        }

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, resp);
    }
}
