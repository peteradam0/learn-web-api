package learn.web.api.service.impl;

import learn.web.api.dao.OrganizationMemberInvitationDao;
import learn.web.api.model.OrganizationMemberInvitation;
import learn.web.api.service.OrganizationMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultOrganizationMemberService implements OrganizationMemberService {

    @Autowired
    private OrganizationMemberInvitationDao organizationMemberInvitationDao;

    @Override
    public OrganizationMemberInvitation createOrganizationMember(OrganizationMemberInvitation organizationMemberInvitation) {
        organizationMemberInvitation.setConfirmed(false);
        return organizationMemberInvitationDao.save(organizationMemberInvitation);
    }

    @Override
    public Optional<OrganizationMemberInvitation> getMemberInvitationById(String id) {
        return organizationMemberInvitationDao.findById(id);
    }

    @Override
    public void updateOrganizationMember(OrganizationMemberInvitation organizationMemberInvitation) {
        Optional<OrganizationMemberInvitation> toDelete = organizationMemberInvitationDao.findById(organizationMemberInvitation.getId());
        toDelete.ifPresent(memberInvitation -> organizationMemberInvitationDao.delete(memberInvitation));
        organizationMemberInvitationDao.save(organizationMemberInvitation);
    }
}
