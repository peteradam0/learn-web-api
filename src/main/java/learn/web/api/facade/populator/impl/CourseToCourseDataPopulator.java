package learn.web.api.facade.populator.impl;

import learn.web.api.facade.ChapterFacade;
import learn.web.api.facade.dto.CourseData;
import learn.web.api.facade.dto.OrganizationData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.model.Course;
import learn.web.api.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseToCourseDataPopulator implements Populator<Course, CourseData> {

    @Autowired
    private ChapterFacade chapterFacade;

    @Autowired
    private OrganizationToOrganizationDataPopulator organizationToOrganizationDataPopulator;

    @Override
    public void populate(Course source, CourseData target) {
        target.setTitle(source.getTitle());
        target.setId(source.getId());
        target.setCategory(source.getCategoryId());
        target.setImageUrl(source.getImageUrl());
        target.setDescription(source.getDescription());
        target.setPublished(source.isPublished());
        target.setVideoUrl(source.getVideoUrl());
        target.setCreatedAt(String.valueOf(source.getCreatedAt()));
        target.setChapterData(chapterFacade.getChapterData(source.getId()));
        if(source.getOrganization() != null){
            populateOrganization(source.getOrganization(), target);
        }
    }

    private void populateOrganization(Organization organization, CourseData courseData) {
        OrganizationData organizationData = new OrganizationData();
        organizationToOrganizationDataPopulator.populate(organization, organizationData);
        courseData.setOrganization(organizationData);
    }
}
