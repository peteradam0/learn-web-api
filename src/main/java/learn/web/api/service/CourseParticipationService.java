package learn.web.api.service;

import learn.web.api.model.CourseParticipation;

import java.util.List;

public interface CourseParticipationService {
    void createParticipation(CourseParticipation courseParticipation);

    CourseParticipation getParticipation(String courseId, String currentUserId);

    List<CourseParticipation> getParticipations (String currentUserId);

    void createChapterParticipation(String courseId, String chapterId, String currentUserId);
}
