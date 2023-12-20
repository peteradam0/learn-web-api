package learn.web.api.service.impl;

import learn.web.api.daos.OrganizationDao;
import learn.web.api.model.Organization;
import learn.web.api.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultOrganizationService implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public Organization createOrganization(Organization organization) {
        Organization foundOrganizations = organizationDao.findOrganizationByName(organization.getName());

        if (foundOrganizations == null) {
            return organizationDao.save(organization);
        }
        throw new RuntimeException("Organization already exists, create failed");
    }

    @Override
    public List<Organization> getOrganizations() {
        return organizationDao.findAll();
    }

    @Override
    public void deleteOrganization(String name) {
        Organization foundOrganizations = organizationDao.findOrganizationByName(name);
        if(foundOrganizations != null){
            organizationDao.delete(foundOrganizations);
        }else{
            throw new RuntimeException("Deletion failed ");
        }
    }

    @Override
    public Organization getOrganizationByName(String name) {
        return organizationDao.findOrganizationByName(name);
    }
}
