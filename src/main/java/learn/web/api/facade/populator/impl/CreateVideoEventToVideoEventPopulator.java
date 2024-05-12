package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dto.CreateEventData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.model.User;
import learn.web.api.model.VideoEvent;
import learn.web.api.service.OrganizationService;
import learn.web.api.service.SessionService;
import learn.web.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CreateVideoEventToVideoEventPopulator implements Populator<CreateEventData, VideoEvent> {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @Override
    public void populate(CreateEventData source, VideoEvent target) {
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setImageUrl(source.getImageUrl());
        target.setActive(false);
        target.setRoomId("");
        target.setOrganizer(userService.getUserByClerkId(sessionService.getCurrentUserId()));
        target.setUsers(getUsers(source.getUsers(),source.getOrganization()));
        target.setOrganization(organizationService.getOrganizationByName(source.getOrganization()));
        target.setDate(source.getDate());
    }

    private List<User> getUsers(List<String> userEmails, String organization) {
        if (ListUtils.isEmpty(userEmails) && Objects.equals(organization, "Public")) {
            return userService.getUsers();
        } else if (ListUtils.isEmpty(userEmails)) {
            return new ArrayList<>();
        }else {
            List<User> users = new ArrayList<>();
            userEmails.forEach(email -> {
                User user = userService.getUserByEmail(email);
                if (user != null) {
                    users.add(user);
                }
            });

            return users;
        }
    }
}
