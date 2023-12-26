package learn.web.api.facade.impl;

import learn.web.api.facade.CourseParticipationFacade;
import learn.web.api.service.SessionService;
import learn.web.api.facade.dto.CourseParticipationData;
import learn.web.api.facade.populator.impl.ParticipationToParticipationDataPopulator;
import learn.web.api.model.CourseParticipation;
import learn.web.api.service.CourseParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultCourseParticipationFacade implements CourseParticipationFacade {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private CourseParticipationService courseParticipationService;

    @Autowired
    private ParticipationToParticipationDataPopulator participationToParticipationDataPopulator;

    @Override
    public void createParticipation(String courseId) {
        CourseParticipation courseParticipation = new CourseParticipation();
        courseParticipation.setUserId(sessionService.getCurrentUserId());
        courseParticipation.setCourseId(courseId);
        courseParticipationService.createParticipation(courseParticipation);
    }

    @Override
    public CourseParticipationData getParticipation(String courseId) {
        CourseParticipation courseParticipation = courseParticipationService.getParticipation(courseId, sessionService.getCurrentUserId());
        CourseParticipationData courseParticipationData = new CourseParticipationData();
        participationToParticipationDataPopulator.populate(courseParticipation, courseParticipationData);
        return courseParticipationData;
    }

    @Override
    public List<CourseParticipationData> getAllParticipations() {
        List<CourseParticipation> courseParticipations = courseParticipationService.getParticipations(sessionService.getCurrentUserId());

        List<CourseParticipationData> courseParticipationDataList = new ArrayList<>();
        if (courseParticipations != null) {
            for (CourseParticipation participation : courseParticipations) {
                CourseParticipationData courseParticipationData = new CourseParticipationData();
                participationToParticipationDataPopulator.populate(participation, courseParticipationData);
                courseParticipationDataList.add(courseParticipationData);
            }
        }

        return courseParticipationDataList;
    }

    @Override
    public void createChapterParticipation(String courseId, String chapterId) {
        courseParticipationService.createChapterParticipation(courseId, chapterId, sessionService.getCurrentUserId());
    }
}
