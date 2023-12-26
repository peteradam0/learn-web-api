package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dto.CanvasUser;
import learn.web.api.facade.dto.UserSuggestionData;
import learn.web.api.facade.populator.Populator;
import org.springframework.stereotype.Component;

@Component
public class CanvasUserToUserSuggestionPopulator implements Populator<CanvasUser, UserSuggestionData> {
    @Override
    public void populate(CanvasUser source, UserSuggestionData target) {
        target.setEmail(source.getLogin_id());
    }
}
