package learn.web.api.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import learn.web.api.utils.JwtValidator;
import learn.web.api.utils.TokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtValidator jwtValidator;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authTokenHeader = request.getHeader("Authorization");
        String token = TokenExtractor.extractToken(authTokenHeader);
        String userId = jwtValidator.getUserIdfromToken(token);

        request.getSession().setAttribute("userId",userId);
        System.out.println(userId+" was set on the session");
        filterChain.doFilter(request, response);
    }
}
