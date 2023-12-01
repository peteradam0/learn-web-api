package learn.web.api.daos;

import learn.web.api.models.Course;
import learn.web.api.models.CourseParticipation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseParticipationDao extends MongoRepository<CourseParticipation, String> {
}
