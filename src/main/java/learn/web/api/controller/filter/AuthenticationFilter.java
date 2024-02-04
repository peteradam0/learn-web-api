package learn.web.api.controller.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import learn.web.api.utils.JwtValidator;
import learn.web.api.utils.TokenExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Arrays;
import java.util.List;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    private final List<String> publicRoutes = Arrays.asList(
            "/api/v1/webhooks",
            "/swagger-ui/index.html",
            "/learn-web-docs/swagger-config",
            "/learn-web-docs",
            "/swagger-ui/swagger-initializer.js",
            "/api/v1/organizations/send",
            "/api/v1/liveweb"
    );

    @Autowired
    private JwtValidator jwtValidator;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return publicRoutes.contains(request.getServletPath());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {

        try {
            String authTokenHeader = request.getHeader("Authorization");
            String token = TokenExtractor.extractToken(authTokenHeader);
            String userId = jwtValidator.getUserfromToken(token);

            request.getSession().setAttribute("userId", userId);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            LOGGER.info(e.getMessage());
        }

    }
}
