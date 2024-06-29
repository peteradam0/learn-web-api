package learn.web.api.service;

import learn.web.api.facade.dto.CanvasUser;
import learn.web.api.model.Organization;
import learn.web.api.model.OrganizationMemberInvitation;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {
    void createOrganization(Organization organization);

    List<Organization> getOrganizations();

    List<Organization> getOrganizationsExclude(String organizationToExclude);

    void deleteOrganization(String name);

    Organization getOrganizationByName(String name);

    Optional<Organization> getOrganizationById(String id);

    void addMember(OrganizationMemberInvitation updatedInvitation);

    List<Organization> getOrganizationsOfMember(String memberId);

    List<CanvasUser> getAllCanvasUsers(String canvasDomain);
}

