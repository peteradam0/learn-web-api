package learn.web.api.facades.populators.impl;

import learn.web.api.facades.dtos.WebhookUserData;
import learn.web.api.facades.populators.Populator;
import learn.web.api.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserDataToUserPopulator implements Populator<WebhookUserData, User> {
    @Override
    public void populate(WebhookUserData source, User target) {
        target.setClerkId(source.getData().getId());
        target.setEmail(source.getData().getEmail_addresses().get(0).getEmail_address());
        target.setUsername(source.getData().getUsername());
    }
}
