package learn.web.api.controller.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
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
import java.util.Optional;

@Component
public class CanvasAuthFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(CanvasAuthFilter.class);

    @Autowired
    private JwtValidator jwtValidator;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().contains("/suggestions");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {

        try {
            String token = request.getHeader("csrf_token");

            if (token.isEmpty()) {
                throw new RuntimeException("Canvas toke not found");
            }
            request.getSession().setAttribute("CanvasToken", TokenExtractor.extractToken(token));
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            LOGGER.info(e.getMessage());
        }

    }

    public Optional<String> readCookie(String key, HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(c -> key.equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }
}
