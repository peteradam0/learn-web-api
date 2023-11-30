package learn.web.api.facades.populators.impl;

import learn.web.api.facades.dtos.UserData;
import learn.web.api.facades.populators.Populator;
import learn.web.api.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserDataToUserPopulator implements Populator<UserData, User> {
    @Override
    public void populate(UserData source, User target) {
        target.setClerkId(source.getData().getId());
        target.setEmail(source.getData().getEmail_addresses().get(0).getEmail_address());
    }
}
