package learn.web.api.facades;

import learn.web.api.facades.dtos.OrganizationData;
import learn.web.api.facades.dtos.OrganizationMemberData;

import java.util.List;

public interface OrganizationFacade {
    OrganizationData createOrganization(OrganizationData organizationData);

    List<OrganizationData> getOrganizations();

    void deleteOrganization(String name);

    void addMemberToOrganization(OrganizationMemberData organizationMemberData);
}
