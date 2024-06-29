package learn.web.api.facade.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class CanvasRequestData {
    private String canvasDomain;

    public String getCanvasDomain() {
        return this.canvasDomain;
    }
}
