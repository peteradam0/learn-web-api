package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dtos.CourseParticipationData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.models.CourseParticipation;
import org.springframework.stereotype.Component;

@Component
public class ParticipationToParticipationDataPopulator implements Populator<CourseParticipation, CourseParticipationData> {
    @Override
    public void populate(CourseParticipation source, CourseParticipationData target) {
        target.setCourseId(source.getCourseId());
        target.setUserId(source.getUserId());
        target.setCompletedChapterIds(source.getCompletedChapterIds());
    }
}
