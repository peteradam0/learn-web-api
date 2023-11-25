package learn.web.api.daos;

import learn.web.api.models.Chapter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChapterDao extends MongoRepository<Chapter, String> {
}
