package learn.web.api.facade.impl;

import learn.web.api.facade.ChapterFacade;
import learn.web.api.facade.dtos.ChapterData;
import learn.web.api.facade.dtos.CreateChapterData;
import learn.web.api.facade.populator.impl.ChapterDataToChapterPopulator;
import learn.web.api.facade.populator.impl.ChapterToChapterDataPopulator;
import learn.web.api.facade.populator.impl.CreateChapterDataToChapterPopulator;
import learn.web.api.model.Chapter;
import learn.web.api.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultChapterFacade implements ChapterFacade {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private CreateChapterDataToChapterPopulator CreateChapterDataToChapterPopulator;

    @Autowired
    private ChapterToChapterDataPopulator chapterToChapterDataPopulator;

    @Autowired
    private ChapterDataToChapterPopulator chapterDataToChapterPopulator;

    @Override
    public ChapterData createChapter(String courseId, CreateChapterData createChapterData) {
        Chapter chapter = new Chapter();
        chapter.setCourseId(courseId);
        CreateChapterDataToChapterPopulator.populate(createChapterData, chapter);

        Chapter createdChapter = chapterService.createChapter(chapter);
        ChapterData result = new ChapterData();
        chapterToChapterDataPopulator.populate(createdChapter, result);

        return result;
    }

    @Override
    public void deleteChapter(String chapterId) {
        chapterService.deleteChapter(chapterId);
    }

    @Override
    public List<ChapterData> getChapterData(String courseId) {
        List<Chapter> chapterList = chapterService.getChaptersOfCourse(courseId);
        List<ChapterData> chapterDataList = new ArrayList<>();

        for (Chapter chapter : chapterList) {
            ChapterData chapterData = new ChapterData();
            chapterToChapterDataPopulator.populate(chapter, chapterData);
            chapterDataList.add(chapterData);
        }

        return chapterDataList;
    }

    @Override
    public ChapterData updateChapter(ChapterData chapterData) {
        Chapter chapterToUpdateTo = new Chapter();
        chapterDataToChapterPopulator.populate(chapterData, chapterToUpdateTo);
        Chapter chapter = chapterService.updateChapter(chapterToUpdateTo);

        chapterToChapterDataPopulator.populate(chapter, chapterData);
        return chapterData;
    }
}
