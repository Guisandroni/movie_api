package dev.guisandroni.movieapi.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecutiryFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if(Strings.isNotEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer ")){
            String token  = authorizationHeader.substring("Bearer ".length());

            
            //config jwt
           // Optional<JWTUserData>jwtUserData = TokenService.verifyToken(token);
            
            filterChain.doFilter(request, response);
            
            
        } else{
            filterChain.doFilter(request,response);
        }
    }
}
