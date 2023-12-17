package learn.web.api.facades.impl;

import learn.web.api.facades.OrganizationFacade;
import learn.web.api.facades.dtos.OrganizationData;
import learn.web.api.facades.populators.impl.OrganizationDataToOrganizationPopulator;
import learn.web.api.facades.populators.impl.OrganizationToOrganizationDataPopulator;
import learn.web.api.models.Organization;
import learn.web.api.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultOrganizationFacade implements OrganizationFacade {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationToOrganizationDataPopulator organizationToOrganizationDataPopulator;

    @Autowired
    private OrganizationDataToOrganizationPopulator organizationDataToOrganizationPopulator;

    @Override
    public OrganizationData createOrganization(OrganizationData organizationData) {
        Organization organization = new Organization();
        organizationDataToOrganizationPopulator.populate(organizationData, organization);
        organizationService.createOrganization(organization);
        return organizationData;
    }

    @Override
    public List<OrganizationData> getOrganizations() {

        List<OrganizationData> organizationDataList = new ArrayList<>();
        for (Organization organization : organizationService.getOrganizations()) {
            OrganizationData organizationData = new OrganizationData();
            organizationToOrganizationDataPopulator.populate(organization, organizationData);
            organizationDataList.add(organizationData);
        }

        return organizationDataList;
    }

    @Override
    public OrganizationData deleteOrganization(OrganizationData organizationData) {
        Organization organization = new Organization();
        organizationDataToOrganizationPopulator.populate(organizationData, organization);
        organizationService.deleteOrganization(organization);
        return organizationData;
    }
}
