package learn.web.api.services;

import learn.web.api.models.Chapter;

public interface ChapterService {

    Chapter createChapter(Chapter chapter);

    void deleteChapter(String chapterId);
}
