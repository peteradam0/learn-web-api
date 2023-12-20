package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dto.CourseAuthorData;
import learn.web.api.facade.dto.UserData;
import learn.web.api.facade.populator.Populator;
import org.springframework.stereotype.Component;

@Component
public class UserDataToCourseAuthorData implements Populator<UserData, CourseAuthorData> {
    @Override
    public void populate(UserData source, CourseAuthorData target) {
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setImageUrl(source.getImageUrl());
    }
}
