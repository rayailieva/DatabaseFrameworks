package fastfood.domain.entities.base;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {

    private Integer id;

    public BaseEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
