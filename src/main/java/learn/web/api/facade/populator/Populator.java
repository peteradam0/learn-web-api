package learn.web.api.facade.populator;

public interface Populator<T,U>{
    void populate(T source, U target);
}

