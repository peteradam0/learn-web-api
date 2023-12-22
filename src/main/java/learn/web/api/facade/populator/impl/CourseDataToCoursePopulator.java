package learn.web.api.facade.populator.impl;

import learn.web.api.facade.OrganizationFacade;
import learn.web.api.facade.SessionFacade;
import learn.web.api.facade.dto.CourseCreateRequestData;
import learn.web.api.facade.dto.OrganizationData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.model.Course;
import learn.web.api.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseDataToCoursePopulator implements Populator<CourseCreateRequestData, Course> {

    @Autowired
    private SessionFacade sessionFacade;

    @Autowired
    private OrganizationFacade organizationFacade;

    @Autowired
    private OrganizationDataToOrganizationPopulator organizationDataToOrganizationPopulator;

    @Override
    public void populate(CourseCreateRequestData source, Course target) {
        target.setTitle(source.getTitle());
        target.setUserId(sessionFacade.getCurrentUserId());
        target.setDescription(source.getDescription());
        target.setImageUrl(source.getImageUrl());
        target.setVideoUrl(source.getVideoUrl());
        target.setCategoryId(source.getCategory());
        populateOrganisationData(source, target);
    }

    private void populateOrganisationData(CourseCreateRequestData source, Course target) {
        OrganizationData organizationData = organizationFacade.getOrganizationByName(source.getOrganization());
        if (organizationData != null) {
            Organization organization = new Organization();
            organizationDataToOrganizationPopulator.populate(organizationData, organization);
            target.setOrganization(organization);
        }

    }
}
