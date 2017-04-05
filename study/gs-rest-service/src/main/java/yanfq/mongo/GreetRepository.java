package yanfq.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import yanfq.domain.Greet;

import java.util.List;

/**
 * Created by yanfq on 2017/3/25.
 */
public interface GreetRepository extends MongoRepository<Greet, String> {
     public List<Greet> findByName(String name);
}
