package learn.web.api.services;

import learn.web.api.models.Chapter;

import java.util.List;

public interface ChapterService {

    Chapter createChapter(Chapter chapter);

    void deleteChapter(String chapterId);

    List<Chapter> getChaptersOfCourse(String courseId);

    Chapter updateChapter(Chapter chapter);
}
