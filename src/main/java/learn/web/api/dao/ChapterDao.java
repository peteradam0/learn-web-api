package learn.web.api.dao;

import learn.web.api.model.Chapter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChapterDao extends MongoRepository<Chapter, String> {

    List<Chapter> findChapterByCourseId(String courseId);
}
