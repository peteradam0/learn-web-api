package learn.web.api.facades;

import learn.web.api.facades.dtos.CourseParticipationData;

import java.util.List;

public interface CourseParticipationFacade {
    void createParticipation(String courseId);
    CourseParticipationData getParticipation(String courseId);

    List<CourseParticipationData> getAllParticipations();

    void createChapterParticipation(String courseId, String chapterId);
}
