package learn.web.api.facades.exceptions;

public class FacadeLayerException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FacadeLayerException(String msg) {
        super(msg);
    }
}
