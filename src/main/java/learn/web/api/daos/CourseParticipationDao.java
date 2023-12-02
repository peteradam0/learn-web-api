package learn.web.api.daos;

import learn.web.api.models.CourseParticipation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseParticipationDao extends MongoRepository<CourseParticipation, String> {

    CourseParticipation getCourseParticipationByCourseIdAndAndUserId(String courseId, String userId);
    List<CourseParticipation> getCourseParticipationByUserId(String userId);
}
