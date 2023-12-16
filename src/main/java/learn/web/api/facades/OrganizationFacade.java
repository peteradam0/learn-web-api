package learn.web.api.facades;

import learn.web.api.facades.dtos.OrganizationData;

import java.util.List;

public interface OrganizationFacade {
    OrganizationData createOrganization(OrganizationData organizationData);

    List<OrganizationData> getOrganizations();
}
