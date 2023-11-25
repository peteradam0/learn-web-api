package learn.web.api.daos;

import learn.web.api.models.Chapter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChapterDao extends MongoRepository<Chapter, String> {

    List<Chapter> findChapterByCourseId(String courseId);
}
