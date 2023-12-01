package learn.web.api.facades.populators.impl;

import learn.web.api.facades.dtos.CourseParticipationData;
import learn.web.api.facades.populators.Populator;
import learn.web.api.models.CourseParticipation;
import org.springframework.stereotype.Component;

@Component
public class ParticipationToParticipationDataPopulator implements Populator<CourseParticipation, CourseParticipationData> {
    @Override
    public void populate(CourseParticipation source, CourseParticipationData target) {
        target.setCourseId(source.getCourseId());
        target.setUserId(source.getUserId());
    }
}
