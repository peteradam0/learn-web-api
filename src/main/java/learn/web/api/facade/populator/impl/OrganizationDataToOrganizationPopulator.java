package learn.web.api.facade.populator.impl;

import learn.web.api.service.SessionService;
import learn.web.api.facade.dto.OrganizationData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationDataToOrganizationPopulator implements Populator<OrganizationData, Organization> {

    @Autowired
    private SessionService sessionService;
    @Override
    public void populate(OrganizationData source, Organization target) {
        target.setName(source.getName());
        target.setAdminId(sessionService.getCurrentUserId());
        target.setImageUrl(source.getImageUrl());
    }
}
