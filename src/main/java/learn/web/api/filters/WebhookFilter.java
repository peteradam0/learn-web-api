package learn.web.api.filters;

import com.svix.Webhook;
import com.svix.exceptions.WebhookVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Component
public class WebhookFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         if (new AntPathMatcher().match("/api/v2/webhooks", request.getServletPath())){
             Webhook webhook = new Webhook("whsec_L8jEQl8bY637Puw9Qv2wea50utiXA+o3");

             HashMap<String, List<String>> headerMap = new HashMap<String, List<String>>();
             headerMap.put("svix-id", Arrays.asList(request.getHeader("svix-id")));
             headerMap.put("svix-timestamp", Arrays.asList(request.getHeader("svix-id")));
             headerMap.put("svix-signature", Arrays.asList(request.getHeader("svix-signature")));

             BiPredicate<String, String> biPredicate = (n, s) ->
             {
                 return true;
             };

             HttpHeaders headers = HttpHeaders.of(headerMap, biPredicate);
             try {
                 webhook.verify(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())), headers);
             } catch (WebhookVerificationException e) {
                 throw new RuntimeException(e);
             }
             filterChain.doFilter(request, response);
         }else{
             filterChain.doFilter(request, response);
         }

    }
}
