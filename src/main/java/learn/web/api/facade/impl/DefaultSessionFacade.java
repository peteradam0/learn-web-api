package learn.web.api.facade.impl;

import jakarta.servlet.http.HttpSession;
import learn.web.api.facade.SessionFacade;
import learn.web.api.facade.exceptions.FacadeLayerException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DefaultSessionFacade implements SessionFacade {

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
}
