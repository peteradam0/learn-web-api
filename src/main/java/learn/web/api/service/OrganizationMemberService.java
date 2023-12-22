package learn.web.api.service;

import learn.web.api.model.OrganizationMemberInvitation;

import java.util.Optional;

public interface OrganizationMemberService {
    OrganizationMemberInvitation createOrganizationMember(OrganizationMemberInvitation organizationMemberInvitation);

    Optional<OrganizationMemberInvitation> getMemberInvitationById(String id);

    OrganizationMemberInvitation updateOrganizationMember(OrganizationMemberInvitation organizationMemberInvitation);
}
