package learn.web.api.filters;

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

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    private JwtValidator jwtValidator;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain){

        try{
            String authTokenHeader = request.getHeader("Authorization");
            String token = TokenExtractor.extractToken(authTokenHeader);
            String userId = jwtValidator.getUserIdfromToken(token);

            request.getSession().setAttribute("userId",userId);

            filterChain.doFilter(request, response);
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            LOGGER.info(e.getMessage());
        }

    }
}
