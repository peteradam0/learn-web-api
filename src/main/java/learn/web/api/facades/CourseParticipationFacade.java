package learn.web.api.facades;

import learn.web.api.facades.dtos.CourseParticipationData;

public interface CourseParticipationFacade {
    void createParticipation(String courseId);
    CourseParticipationData getParticipation(String courseId);
}
