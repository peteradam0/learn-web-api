package learn.web.api.service.impl;

import learn.web.api.dao.VideoEventDao;
import learn.web.api.model.VideoEvent;
import learn.web.api.service.VideoEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultVideoEventService implements VideoEventService {

    @Autowired
    private VideoEventDao videoEventDao;

    @Override
    public VideoEvent createVideoEvent(VideoEvent videoEventToCreate) {
        return videoEventDao.save(videoEventToCreate);
    }
}
