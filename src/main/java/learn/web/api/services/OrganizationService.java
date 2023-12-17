package learn.web.api.services;

import learn.web.api.models.Organization;

import java.util.List;

public interface OrganizationService {
    Organization createOrganization(Organization organization);

    List<Organization> getOrganizations();

    void deleteOrganization(String name);
}

