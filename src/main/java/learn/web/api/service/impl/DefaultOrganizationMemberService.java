package learn.web.api.service.impl;

import learn.web.api.dao.OrganizationMemberInvitationDao;
import learn.web.api.model.OrganizationMemberInvitation;
import learn.web.api.service.OrganizationMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultOrganizationMemberService implements OrganizationMemberService {

    @Autowired
    private OrganizationMemberInvitationDao organizationMemberInvitationDao;

    @Override
    public OrganizationMemberInvitation createOrganizationMember(OrganizationMemberInvitation organizationMemberInvitation) {
        organizationMemberInvitation.setConfirmed(false);
        return organizationMemberInvitationDao.save(organizationMemberInvitation);
    }
}
