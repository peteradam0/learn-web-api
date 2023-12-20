package learn.web.api.service;

import learn.web.api.model.Chapter;

import java.util.List;

public interface ChapterService {

    Chapter createChapter(Chapter chapter);

    void deleteChapter(String chapterId);

    List<Chapter> getChaptersOfCourse(String courseId);

    Chapter updateChapter(Chapter chapter);
}
