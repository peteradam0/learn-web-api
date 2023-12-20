package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dtos.OrganizationData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.model.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationToOrganizationDataPopulator implements Populator<Organization, OrganizationData> {
    @Override
    public void populate(Organization source, OrganizationData target) {
        target.setName(source.getName());
        target.setImageUrl(source.getImageUrl());
        target.setId(source.getId());
    }
}
