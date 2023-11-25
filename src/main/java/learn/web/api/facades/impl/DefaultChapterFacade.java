package learn.web.api.facades.impl;

import learn.web.api.facades.ChapterFacade;
import learn.web.api.facades.dtos.ChapterData;
import learn.web.api.facades.dtos.CreateChapterData;
import learn.web.api.facades.populators.impl.ChapterToChapterDataPopulator;
import learn.web.api.facades.populators.impl.CreateChapterDataToChapterPopulator;
import learn.web.api.models.Chapter;
import learn.web.api.services.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultChapterFacade implements ChapterFacade {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private CreateChapterDataToChapterPopulator chapterDataToChapterPopulator;

    @Autowired
    private ChapterToChapterDataPopulator chapterToChapterDataPopulator;

    @Override
    public ChapterData createChapter(String courseId, CreateChapterData createChapterData) {

        Chapter chapter = new Chapter();
        chapter.setCourseId(courseId);
        chapterDataToChapterPopulator.populate(createChapterData, chapter);


        Chapter createdChapter = chapterService.createChapter(chapter);
        ChapterData result = new ChapterData();
        chapterToChapterDataPopulator.populate(createdChapter, result);

        return result;
    }

    @Override
    public void deleteChapter(String chapterId) {
        chapterService.deleteChapter(chapterId);
    }
}
