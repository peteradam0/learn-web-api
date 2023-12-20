package learn.web.api.facade.impl;

import learn.web.api.facade.EmailFacade;
import learn.web.api.facade.OrganizationFacade;
import learn.web.api.facade.UserFacade;
import learn.web.api.facade.dto.OrganizationData;
import learn.web.api.facade.dto.OrganizationMemberData;
import learn.web.api.facade.dto.UserData;
import learn.web.api.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultEmailFacade implements EmailFacade {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private OrganizationFacade organizationFacade;

    @Override
    public void sendOrganizationMemberInvitation(OrganizationMemberData organizationMemberData) {
        UserData userData = userFacade.getUserDataById(organizationMemberData.getUserEmail());
        OrganizationData organizationData = organizationFacade.getOrganizationByName(organizationMemberData.getOrganizationName());
        emailService.sengOrganizationInvite(organizationMemberData.getUserEmail(), "Organization Invitation", organizationData.getId(), organizationData.getName());
    }
}
