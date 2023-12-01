package learn.web.api.services.impl;

import learn.web.api.daos.CourseParticipationDao;
import learn.web.api.models.CourseParticipation;
import learn.web.api.services.CourseParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultCourseParticipationService implements CourseParticipationService {

    @Autowired
    private CourseParticipationDao courseParticipationDao;

    @Override
    public void createParticipation(CourseParticipation courseParticipation) {
        courseParticipationDao.save(courseParticipation);
    }

    @Override
    public CourseParticipation getParticipation(String courseId, String currentUserId) {
        CourseParticipation courseParticipation = courseParticipationDao.getCourseParticipationByCourseIdAndAndUserId(courseId, currentUserId);

        if (courseParticipation.getCourseId() != null) {
            return courseParticipation;
        }
        throw new RuntimeException("No participation found");
    }
}
