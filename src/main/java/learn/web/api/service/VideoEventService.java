package learn.web.api.service;

import learn.web.api.model.VideoEvent;

import java.util.List;

public interface VideoEventService {
    VideoEvent createVideoEvent(VideoEvent videoEventToCreate);

    List<VideoEvent> getVideoEvents();

    void removeVideoEvent(String name, String organization);

    void startEvent(String organization, String username, String roomId);
}
