package learn.web.api.service;

import learn.web.api.model.Organization;

import java.util.List;

public interface OrganizationService {
    Organization createOrganization(Organization organization);

    List<Organization> getOrganizations();

    void deleteOrganization(String name);

    Organization getOrganizationByName(String name);
}

