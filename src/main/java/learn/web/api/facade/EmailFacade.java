package learn.web.api.facade;

import learn.web.api.facade.dto.OrganizationMemberData;

public interface EmailFacade {
    void sendOrganizationMemberInvitation(OrganizationMemberData organizationMemberData);
}
