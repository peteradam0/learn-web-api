package learn.web.api.services;

import learn.web.api.models.CourseParticipation;

public interface CourseParticipationService {
    void createParticipation(CourseParticipation courseParticipation);

    CourseParticipation getParticipation(String courseId, String currentUserId);

    void createChapterParticipation(String courseId, String chapterId, String currentUserId);
}
