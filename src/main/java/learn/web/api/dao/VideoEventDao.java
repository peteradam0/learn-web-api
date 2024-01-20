package learn.web.api.dao;

import learn.web.api.model.VideoEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoEventDao extends MongoRepository<VideoEvent, String> {
}
