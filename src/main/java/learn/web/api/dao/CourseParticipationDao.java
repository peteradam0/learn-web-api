package learn.web.api.dao;

import learn.web.api.model.CourseParticipation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseParticipationDao extends MongoRepository<CourseParticipation, String> {

    CourseParticipation findByCourseIdAndUserId(String courseId, String userId);
    List<CourseParticipation> getCourseParticipationByUserId(String userId);
}
