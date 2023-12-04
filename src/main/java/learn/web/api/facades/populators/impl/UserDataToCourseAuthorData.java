package learn.web.api.facades.populators.impl;

import learn.web.api.facades.dtos.CourseAuthorData;
import learn.web.api.facades.dtos.UserData;
import learn.web.api.facades.populators.Populator;
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
