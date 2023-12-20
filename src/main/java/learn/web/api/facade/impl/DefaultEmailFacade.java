package learn.web.api.facade.impl;

import learn.web.api.facade.EmailFacade;
import learn.web.api.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultEmailFacade implements EmailFacade {

    @Autowired
    private EmailService emailService;

    @Override
    public void sendOrganizationMemberInvitation() {
        emailService.sengOrganizationInvite("adampeter2015@gmail.com", "Hello world");
    }
}
