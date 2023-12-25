package learn.web.api.service;

import learn.web.api.facade.dto.OrganizationData;
import learn.web.api.model.Organization;
import learn.web.api.model.OrganizationMemberInvitation;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {
    Organization createOrganization(Organization organization);

    List<Organization> getOrganizations();

    void deleteOrganization(String name);

    Organization getOrganizationByName(String name);

    Optional<Organization> getOrganizationById(String id);

    void addMember(OrganizationMemberInvitation updatedInvitation);

    List<Organization> getOrganizationsOfMember(String memberId);
}

