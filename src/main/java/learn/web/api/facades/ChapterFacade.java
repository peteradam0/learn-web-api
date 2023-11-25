package learn.web.api.facades;

import learn.web.api.facades.dtos.ChapterData;
import learn.web.api.facades.dtos.CreateChapterData;

public interface ChapterFacade {
    ChapterData createChapter(String courseId, CreateChapterData createChapterData);

    void deleteChapter(String chapterId);
}
