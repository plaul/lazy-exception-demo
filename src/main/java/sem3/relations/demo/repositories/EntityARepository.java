package sem3.relations.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sem3.relations.demo.entities.EntityA;

public interface EntityARepository extends JpaRepository<EntityA,Long> {
}
