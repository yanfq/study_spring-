package yanfq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yanfq.domain.Greet;
import yanfq.mongo.GreetRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by yanfq on 2017/3/25.
 */
@Service
public class GreetService {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetRepository greetRepository;

    public void addInfo(String name){
        Greet greet = new Greet(name,String.format(template,name));
        greetRepository.save(greet);
    }

    public List<Greet> getInfo(String name){
        List<Greet> greets = greetRepository.findAll();
        return greets;
    }
}
