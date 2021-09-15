package sem3.relations.demo.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import sem3.relations.demo.entities.EntityA;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DTO_A {
    String name;
    List<DTO_B> allB_DTOs = new ArrayList<>();

    //Initialize the DTO without the items from the Many-End in EntityB
    public DTO_A(EntityA a, EntityManager em, boolean loadLazyData) {
        name = a.getName();
        if (loadLazyData) {
            allB_DTOs = em.createQuery(
                            "select new " +
                                    "   sem3.relations.demo.dtos.DTO_B(" +
                                    "       eb.name" +
                                    "   ) " +
                                    "from EntityB eb " +
                                    "join eb.a ana ", DTO_B.class)
                    .getResultList();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DTO_B> getAllB_DTOs() {
        return allB_DTOs;
    }

    public void setAllB_DTOs(List<DTO_B> allB_DTOs) {
        this.allB_DTOs = allB_DTOs;
    }
}

