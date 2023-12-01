package learn.web.api.facades.populators.impl;

import learn.web.api.facades.dtos.UserData;
import learn.web.api.facades.populators.Populator;
import learn.web.api.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDataPopulator implements Populator<User, UserData> {
    @Override
    public void populate(User source, UserData target) {
        target.setUsername(source.getUsername());
        target.setEmail(source.getEmail());
    }
}
