package learn.web.api.dao;

import learn.web.api.facade.dto.CanvasCourse;
import learn.web.api.facade.dto.CanvasUser;

import java.util.List;

public interface CanvasDao {
    List<CanvasUser> findAllCanvasUsers(String canvasDomain);

    List<CanvasCourse> findAllCanvasCourses( String canvasDomain);
}
