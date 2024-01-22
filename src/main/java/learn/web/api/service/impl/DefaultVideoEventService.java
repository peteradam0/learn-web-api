package learn.web.api.service.impl;

import learn.web.api.dao.VideoEventDao;
import learn.web.api.exception.ServiceLayerException;
import learn.web.api.model.Organization;
import learn.web.api.model.User;
import learn.web.api.model.VideoEvent;
import learn.web.api.service.OrganizationService;
import learn.web.api.service.SessionService;
import learn.web.api.service.UserService;
import learn.web.api.service.VideoEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DefaultVideoEventService implements VideoEventService {

    @Autowired
    private VideoEventDao videoEventDao;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Override
    public VideoEvent createVideoEvent(VideoEvent videoEventToCreate) {
        return videoEventDao.save(videoEventToCreate);
    }

    @Override
    public List<VideoEvent> getVideoEvents() {
        return videoEventDao.findAll();
    }

    @Override
    public void removeVideoEvent(String name, String organization) {
        Organization org = organizationService.getOrganizationByName(organization);
        videoEventDao.removeVideoEventByNameAndOrganization(name, org);
    }

    @Override
    public void startEvent(String organization, String name, String roomId) {
        Organization org = organizationService.getOrganizationByName(organization);
        VideoEvent videoEvent = videoEventDao.getVideoEventByNameAndOrganization(name, org);

        if (!videoEvent.isActive()) {
            videoEventDao.delete(videoEvent);
            videoEvent.setRoomId(roomId);
            videoEvent.setActive(true);
            videoEventDao.save(videoEvent);
        }

        throw new ServiceLayerException("Event already started");
    }

    @Override
    public List<VideoEvent> getVideoEventsForCurrentUser() {
        User user = userService.getUserById(sessionService.getCurrentUserId());
        List<VideoEvent> videoEvents = videoEventDao.getVideoEventByActive(true);

        if (user == null || videoEvents == null) return new ArrayList<>();

        return videoEvents.stream().filter(videoEvent -> !videoEvent.getUsers().contains(user)
                || !(Objects.equals(videoEvent.getOrganizer().getEmail(), user.getEmail()))).toList();
    }
}
