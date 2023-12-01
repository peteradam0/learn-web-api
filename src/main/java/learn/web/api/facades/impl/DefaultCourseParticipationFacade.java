package learn.web.api.facades.impl;

import learn.web.api.facades.CourseParticipationFacade;
import learn.web.api.facades.SessionFacade;
import learn.web.api.facades.dtos.CourseParticipationData;
import learn.web.api.facades.populators.impl.ParticipationToParticipationDataPopulator;
import learn.web.api.models.CourseParticipation;
import learn.web.api.services.CourseParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultCourseParticipationFacade implements CourseParticipationFacade {

    @Autowired
    private SessionFacade sessionFacade;

    @Autowired
    private CourseParticipationService courseParticipationService;

    @Autowired
    private ParticipationToParticipationDataPopulator participationToParticipationDataPopulator;

    @Override
    public void createParticipation(String courseId) {
        CourseParticipation courseParticipation = new CourseParticipation();
        courseParticipation.setUserId(sessionFacade.getCurrentUserId());
        courseParticipation.setCourseId(courseId);
        courseParticipationService.createParticipation(courseParticipation);
    }

    @Override
    public CourseParticipationData getParticipation(String courseId) {
        CourseParticipation courseParticipation = courseParticipationService.getParticipation(courseId, sessionFacade.getCurrentUserId());
        CourseParticipationData courseParticipationData = new CourseParticipationData();
        participationToParticipationDataPopulator.populate(courseParticipation,courseParticipationData);
        return courseParticipationData;
    }
}
