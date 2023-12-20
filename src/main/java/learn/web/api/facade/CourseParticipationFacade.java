package learn.web.api.facade;

import learn.web.api.facade.dtos.CourseParticipationData;

import java.util.List;

public interface CourseParticipationFacade {
    void createParticipation(String courseId);
    CourseParticipationData getParticipation(String courseId);

    List<CourseParticipationData> getAllParticipations();

    void createChapterParticipation(String courseId, String chapterId);
}
