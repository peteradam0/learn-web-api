package learn.web.api.dao;

import learn.web.api.model.Organization;
import learn.web.api.model.VideoEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VideoEventDao extends MongoRepository<VideoEvent, String> {
    void removeVideoEventByNameAndOrganization(String name, Organization organization);

    VideoEvent getVideoEventByNameAndOrganization(String name, Organization organization);

    List<VideoEvent> getVideoEventByActive(boolean active);
}
