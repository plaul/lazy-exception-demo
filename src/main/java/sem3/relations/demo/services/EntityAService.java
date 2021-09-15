package sem3.relations.demo.services;

import org.springframework.stereotype.Service;
import sem3.relations.demo.dtos.DTO_A;
import sem3.relations.demo.entities.EntityA;
import sem3.relations.demo.repositories.EntityARepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class EntityAService {

    EntityARepository aRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public EntityAService(EntityARepository aRepository) {
        this.aRepository = aRepository;
    }

    //Returns a DTO not an Entity Class
    public DTO_A findById(long id, boolean lazyLoad){
        EntityA a = (EntityA) entityManager.find(EntityA.class,id);
        //entityManager.close(); TBD
        return new DTO_A(a,entityManager,lazyLoad);

    }

}
