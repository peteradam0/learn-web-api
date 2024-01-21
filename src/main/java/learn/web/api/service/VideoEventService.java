package learn.web.api.service;

import learn.web.api.model.VideoEvent;

import java.util.List;

public interface VideoEventService {
    VideoEvent createVideoEvent(VideoEvent videoEventToCreate);

    List<VideoEvent> getVideoEvents();
}
