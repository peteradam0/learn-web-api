package learn.web.api.exception;

public class ServiceLayerException extends RuntimeException{
    public ServiceLayerException(String message) {
        super(message);
    }
}
