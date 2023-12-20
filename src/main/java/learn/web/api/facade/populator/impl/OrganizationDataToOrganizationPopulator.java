package learn.web.api.facade.populator.impl;

import learn.web.api.facade.SessionFacade;
import learn.web.api.facade.dtos.OrganizationData;
import learn.web.api.facade.populator.Populator;
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
