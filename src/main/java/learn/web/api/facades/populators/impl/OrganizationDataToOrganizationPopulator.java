package learn.web.api.facades.populators.impl;

import learn.web.api.facades.SessionFacade;
import learn.web.api.facades.dtos.OrganizationData;
import learn.web.api.facades.populators.Populator;
import learn.web.api.models.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationDataToOrganizationPopulator implements Populator<OrganizationData, Organization> {

    @Autowired
    private SessionFacade sessionFacade;
    @Override
    public void populate(OrganizationData source, Organization target) {
        target.setName(source.getName());
        target.setAdminId(sessionFacade.getCurrentUserId());
        target.setImageUrl(source.getImageUrl());
    }
}
