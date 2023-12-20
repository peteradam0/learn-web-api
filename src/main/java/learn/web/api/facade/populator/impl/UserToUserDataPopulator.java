package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dtos.UserData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDataPopulator implements Populator<User, UserData> {
    @Override
    public void populate(User source, UserData target) {
        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
        target.setFirstName(source.getFirstname());
        target.setLastName(source.getLastname());
        target.setImageUrl(source.getImageUrl());
        target.setId(source.getId());
        target.setUserRole(source.getUserRole());
        target.setClerkId(source.getClerkId());
    }
}
