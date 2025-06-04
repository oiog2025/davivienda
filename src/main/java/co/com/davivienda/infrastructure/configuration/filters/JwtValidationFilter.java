
package co.com.davivienda.infrastructure.configuration.filters;

import co.com.davivienda.infrastructure.configuration.TokenJwtConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(TokenJwtConfig.HEADER_AUTHORIZATION);

        if (header == null || !header.startsWith(TokenJwtConfig.PREFIX_TOKEN)) {
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace(TokenJwtConfig.PREFIX_TOKEN, "");

        try {
            Claims claims =
                    Jwts.parser()
                            .verifyWith(TokenJwtConfig.SECRET_KEY)
                            .build()
                            .parseSignedClaims(token)
                            .getPayload();
            String usename = claims.getSubject();
            // Object authoritiesClaims = claims.get("roles");

            Collection<? extends GrantedAuthority> authorities =
                    Arrays.asList(
                            new ObjectMapper()
                                    .readValue("[\"ROLE_USER\"]", SimpleGrantedAuthority[].class));

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(usename, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        } catch (JwtException e) {
            Map<String, String> body = new HashMap<>();
            body.put("errorCode", HttpStatus.UNAUTHORIZED.value() + "");
            body.put("messageDescription", HttpStatus.UNAUTHORIZED.toString());

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(TokenJwtConfig.CONTENT_TYPE);
        }
    }
}
