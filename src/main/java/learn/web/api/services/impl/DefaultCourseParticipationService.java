package learn.web.api.services.impl;

import learn.web.api.controllers.CourseParticipationController;
import learn.web.api.daos.CourseParticipationDao;
import learn.web.api.models.CourseParticipation;
import learn.web.api.services.CourseParticipationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultCourseParticipationService implements CourseParticipationService {

    private final Logger LOGGER = LoggerFactory.getLogger(DefaultCourseParticipationService.class);

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

    @Override
    public void createChapterParticipation(String courseId, String chapterId, String currentUserId) {
        CourseParticipation courseParticipation = courseParticipationDao.getCourseParticipationByCourseIdAndAndUserId(courseId, currentUserId);
        List<String> completedChapters = courseParticipation.getCompletedChapterIds();
        if (completedChapters!= null && !completedChapters.contains(chapterId)) {
            completedChapters.add(chapterId);
            courseParticipation.setCompletedChapterIds(completedChapters);
            courseParticipationDao.save(courseParticipation);
        } else if (completedChapters == null) {
            completedChapters = new ArrayList<>();
            completedChapters.add(chapterId);
            courseParticipation.setCompletedChapterIds(completedChapters);
            courseParticipationDao.save(courseParticipation);
        }else{
            LOGGER.debug("Chapter already was completed before");
        }

    }
}
