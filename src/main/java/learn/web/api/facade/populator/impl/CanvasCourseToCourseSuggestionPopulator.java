package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dto.CanvasCourse;
import learn.web.api.facade.dto.CourseSuggestionData;
import learn.web.api.facade.populator.Populator;
import org.springframework.stereotype.Component;

@Component
public class CanvasCourseToCourseSuggestionPopulator implements Populator<CanvasCourse, CourseSuggestionData> {
    @Override
    public void populate(CanvasCourse source, CourseSuggestionData target) {
        target.setTitle(source.getName());
    }
}
