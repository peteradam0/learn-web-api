package learn.web.api.facades.populators.impl;

import learn.web.api.facades.dtos.OrganizationData;
import learn.web.api.facades.populators.Populator;
import learn.web.api.models.Organization;
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
