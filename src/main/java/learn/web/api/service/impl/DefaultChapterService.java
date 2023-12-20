package learn.web.api.service.impl;

import learn.web.api.dao.ChapterDao;
import learn.web.api.model.Chapter;
import learn.web.api.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class DefaultChapterService implements ChapterService {

    @Autowired
    private ChapterDao chapterDao;

    @Override
    public Chapter createChapter(Chapter chapter) {
        chapter.setCreatedAt(Instant.now());
        return chapterDao.save(chapter);
    }

    @Override
    public void deleteChapter(String chapterId) {
        chapterDao.deleteById(chapterId);
    }

    @Override
    public List<Chapter> getChaptersOfCourse(String courseId) {
        return chapterDao.findChapterByCourseId(courseId);
    }

    @Override
    public Chapter updateChapter(Chapter chapter) {
        chapter.setUpdatedAt(Instant.now());
        return chapterDao.save(chapter);
    }
}
