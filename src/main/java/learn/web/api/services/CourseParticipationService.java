package learn.web.api.services;

import learn.web.api.models.CourseParticipation;

import java.util.List;

public interface CourseParticipationService {
    void createParticipation(CourseParticipation courseParticipation);

    CourseParticipation getParticipation(String courseId, String currentUserId);

    List<CourseParticipation> getParticipations (String currentUserId);

    void createChapterParticipation(String courseId, String chapterId, String currentUserId);
}
