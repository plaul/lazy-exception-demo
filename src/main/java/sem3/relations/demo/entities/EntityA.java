package sem3.relations.demo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EntityA {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String name;

    @OneToMany(mappedBy = "a",cascade = CascadeType.PERSIST)
    List<EntityB> allBs = new ArrayList<>();


    public EntityA(String name) {
        this.name = name;
    }

    public EntityA() { }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void addB(EntityB b){
        allBs.add(b);
        b.setA(this);
    }

    public List<EntityB> getAllBs() {
        return allBs;
    }

    public void setName(String name) {
        this.name = name;
    }
}
