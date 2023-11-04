package learn.web.api.facades.populators;

public interface Populator<T,U>{
    void populate(T source, U target);
}

