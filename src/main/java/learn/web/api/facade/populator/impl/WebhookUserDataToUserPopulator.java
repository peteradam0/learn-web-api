package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dto.ClerkEventData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.model.User;
import org.springframework.stereotype.Component;

@Component
public class WebhookUserDataToUserPopulator implements Populator<ClerkEventData, User> {
    @Override
    public void populate(ClerkEventData source, User target) {
        target.setClerkId(source.getData().getId());
        target.setEmail(source.getData().getEmail_addresses().get(0).getEmail_address());
        target.setUsername(source.getData().getUsername());
        target.setImageUrl(source.getData().getImage_url());
        target.setFirstname(source.getData().getFirst_name());
        target.setLastname(source.getData().getLast_name());
    }
}
