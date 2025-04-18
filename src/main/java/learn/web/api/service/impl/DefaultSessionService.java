package learn.web.api.service.impl;

import jakarta.servlet.http.HttpSession;
import learn.web.api.service.SessionService;
import learn.web.api.facade.exceptions.FacadeLayerException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DefaultSessionService implements SessionService {

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Override
    public String getCurrentUserId() {
        HttpSession session = httpSessionFactory.getObject();
        String userId = (String) session.getAttribute("userId");

        if(StringUtils.hasText(userId)){
            return userId;
        }

        throw new FacadeLayerException("UserId not found on the session");
    }

    @Override
    public String getCanvasToken() {
        HttpSession session = httpSessionFactory.getObject();
        String userId = (String) session.getAttribute("CanvasToken");

        if(StringUtils.hasText(userId)){
            return userId;
        }

        throw new FacadeLayerException("UserId not found on the session");
    }
}
