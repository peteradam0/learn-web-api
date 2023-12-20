package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dtos.WebhookUserData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDataToUserPopulator implements Populator<WebhookUserData, User> {
    @Override
    public void populate(WebhookUserData source, User target) {
        target.setClerkId(source.getData().getId());
        target.setEmail(source.getData().getEmail_addresses().get(0).getEmail_address());
        target.setUsername(source.getData().getUsername());
        target.setImageUrl(source.getData().getImage_url());
        target.setFirstname(source.getData().getFirst_name());
        target.setLastname(source.getData().getLast_name());
    }
}
