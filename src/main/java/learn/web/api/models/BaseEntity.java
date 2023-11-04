package learn.web.api.models;


public class BaseEntity extends AbstractModel {

    private static final long serialVersionUID = 1L;

    private Long id;

    public BaseEntity() {
        this(null);
    }

    public BaseEntity(final Long id) {
        super();
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
