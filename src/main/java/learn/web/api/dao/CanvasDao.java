package learn.web.api.dao;

import learn.web.api.facade.dto.CanvasUser;

import java.util.List;

public interface CanvasDao {
    List<CanvasUser> findAllCanvasUsers();
}
