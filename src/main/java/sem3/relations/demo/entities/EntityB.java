package sem3.relations.demo.entities;

import sem3.relations.demo.entities.EntityA;

import javax.persistence.*;

@Entity
public class EntityB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    EntityA a;
    String name;

    public EntityB(String name) {
        this.name = name;
    }

    public EntityB() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setA(EntityA a) {
        this.a = a;
    }

    public EntityA getA() {
        return a;
    }
}
