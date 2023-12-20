package learn.web.api.facade;

import learn.web.api.facade.dtos.OrganizationData;
import learn.web.api.facade.dtos.OrganizationMemberData;

import java.util.List;

public interface OrganizationFacade {
    OrganizationData createOrganization(OrganizationData organizationData);

    List<OrganizationData> getOrganizations();

    void deleteOrganization(String name);

    void addMemberToOrganization(OrganizationMemberData organizationMemberData);
}
