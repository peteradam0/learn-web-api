package learn.web.api.facades.impl;

import learn.web.api.facades.CourseParticipationFacade;
import learn.web.api.facades.SessionFacade;
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

    @Override
    public void createParticipation(String courseId) {
        CourseParticipation courseParticipation = new CourseParticipation();
        courseParticipation.setUserId(sessionFacade.getCurrentUserId());
        courseParticipation.setCourseId(courseId);
        courseParticipationService.createParticipation(courseParticipation);
    }
}
