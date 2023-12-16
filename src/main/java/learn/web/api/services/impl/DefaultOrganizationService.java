package learn.web.api.services.impl;

import learn.web.api.daos.OrganizationDao;
import learn.web.api.models.Organization;
import learn.web.api.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultOrganizationService implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public Organization createOrganization(Organization organization) {
        return organizationDao.save(organization);
    }

    @Override
    public List<Organization> getOrganizations() {
        return organizationDao.findAll();
    }
}
