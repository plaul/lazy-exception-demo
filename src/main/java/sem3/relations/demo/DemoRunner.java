package sem3.relations.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import sem3.relations.demo.dtos.DTO_A;
import sem3.relations.demo.entities.EntityA;
import sem3.relations.demo.entities.EntityB;
import sem3.relations.demo.repositories.EntityARepository;
import sem3.relations.demo.services.EntityAService;

@Configuration
public class DemoRunner implements CommandLineRunner {

    EntityARepository aRepository;

    @Autowired
    EntityAService serviceA;

    public DemoRunner(EntityARepository aRepository) {
        this.aRepository = aRepository;
    }

    @Override
    public void run(String... args) throws Exception {
       EntityA a1 = new EntityA("Number-1");
       a1.addB(new EntityB(("B-1")));
       a1.addB(new EntityB(("B-2")));
       a1.addB(new EntityB(("B-3")));
       aRepository.save(a1);
       EntityA a = aRepository.findById(1L).get();
       //You can fetch the name
       System.out.println(a.getName());
       try {
           //BUT This line will fail due to its lazy list
           System.out.println("Number of b's: "+a.getAllBs().size());
       } catch (Exception e){
           System.out.println(e.getMessage());
           System.out.println();
           System.out.println("The error above, is the ERROR you will see OFTEN, due to the lazy default value of ONE-TO-MANY");
           System.out.println("Locate the line in the DemoRunner class that lead to the error, and make sure you understand the problem");
           System.out.println("EVEN if it's a bad solution IT IS OK THIS SEMESTER to solve the problem, by changing LAZY ot EAGER");
           System.out.println("");
           System.out.println("The following solution is inspired by this Article: https://vladmihalcea.com/the-best-way-to-handle-the-lazyinitializationexception/");
           System.out.println("See the DTO_A constructor. Im not, however, 100% sure whether the way I obtain the EntityManager in the service is OK");
           System.out.println("FOCUS this window and HIT RETURN to Continue (twice if needed)");
           System.in.read();
       }

        DTO_A aDTO = serviceA.findById(1L,false);
        System.out.println(aDTO.getName());
        System.out.println("Lazy Data Not Loaded: "+ aDTO.getAllB_DTOs().size());

        aDTO = serviceA.findById(1L,true);
        System.out.println(aDTO.getName());
        System.out.println("Lazy Data Was Loaded: "+aDTO.getAllB_DTOs().size());

        aDTO = serviceA.findById(1L,true);
        System.out.println(aDTO.getName());
        System.out.println("Lazy Data Was Loaded: "+aDTO.getAllB_DTOs().size());

    }
}
