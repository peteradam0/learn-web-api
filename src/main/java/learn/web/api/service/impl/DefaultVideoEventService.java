package learn.web.api.service.impl;

import learn.web.api.dao.VideoEventDao;
import learn.web.api.model.Organization;
import learn.web.api.model.VideoEvent;
import learn.web.api.service.OrganizationService;
import learn.web.api.service.VideoEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultVideoEventService implements VideoEventService {

    @Autowired
    private VideoEventDao videoEventDao;

    @Autowired
    private OrganizationService organizationService;

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
}
