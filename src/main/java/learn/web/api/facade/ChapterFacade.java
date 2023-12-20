package learn.web.api.facade;

import learn.web.api.facade.dto.ChapterData;
import learn.web.api.facade.dto.CreateChapterData;

import java.util.List;

public interface ChapterFacade {
    ChapterData createChapter(String courseId, CreateChapterData createChapterData);

    void deleteChapter(String chapterId);

    List<ChapterData> getChapterData(String courseId);

    ChapterData updateChapter(ChapterData chapterData);
}
