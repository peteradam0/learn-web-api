package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dto.UserData;
import learn.web.api.facade.dto.VideoEventData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.model.User;
import learn.web.api.model.VideoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoEventToVideoEventDataPopulator implements Populator<VideoEvent, VideoEventData> {

    @Autowired
    private UserToUserDataPopulator userToUserDataPopulator;

    @Override
    public void populate(VideoEvent source, VideoEventData target) {
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setOrganization(source.getOrganization().getName());
        target.setImageUrl(source.getImageUrl());
        target.setUsers(convertUsers(source.getUsers()));
        target.setActive(source.isActive());
        target.setOrganizer(convertOrganizer(source.getOrganizer()));

        if(StringUtils.hasText(source.getRoomId())){
            target.setRoomId(source.getRoomId());
        }
    }

    private UserData convertOrganizer(User user) {
        UserData organizer = new UserData();
        userToUserDataPopulator.populate(user, organizer);
        return organizer;
    }

    private List<UserData> convertUsers(List<User> users) {
        List<UserData> userDataList = new ArrayList<>();
        users.forEach(user -> {
            UserData userData = new UserData();
            userToUserDataPopulator.populate(user, userData);
            userDataList.add(userData);
        });
        return userDataList;
    }
}
