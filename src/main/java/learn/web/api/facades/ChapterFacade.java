package learn.web.api.facades;

import learn.web.api.facades.dtos.ChapterData;
import learn.web.api.facades.dtos.CreateChapterData;

import java.util.List;

public interface ChapterFacade {
    ChapterData createChapter(String courseId, CreateChapterData createChapterData);

    void deleteChapter(String chapterId);

    List<ChapterData> getChapterData(String courseId);

    ChapterData updateChapter(ChapterData chapterData);
}
