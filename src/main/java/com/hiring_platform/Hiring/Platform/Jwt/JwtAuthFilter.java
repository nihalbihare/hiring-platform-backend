package com.hiring_platform.Hiring.Platform.Jwt;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
 @Component
public class JwtAuthFilter extends OncePerRequestFilter {
    // Inject JwtHelper to handle JWT operations (extract username, validate token, etc.)
    @Autowired
    private JwtHelper jwtHelper;

    // Inject custom UserDetailsService to load user details from DB or memory
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Get Authorization header from the HTTP request
        String requestHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        // Check if the header is present and starts with "Bearer"
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            // Extract token from header by removing "Bearer " prefix
            token = requestHeader.substring(7);
            try {
                // Get username from token
                username = jwtHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                // Token is invalid
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                // Token has expired
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                // Token is malformed
                e.printStackTrace();
            } catch (Exception e) {
                // Other unknown exception
                e.printStackTrace();
            }
        }

        // Proceed if username is extracted and user is not already authenticated
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load user details from database/service
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // Validate the token with the username
            Boolean validateToken = this.jwtHelper.validateToken(token , userDetails);

            // If token is valid (here it checks for null, may need revision)
            if (Boolean.TRUE.equals(validateToken)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null,
                                userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }
        // Continue the filter chain to other filters or controllers
        filterChain.doFilter(request, response);
    }
}
