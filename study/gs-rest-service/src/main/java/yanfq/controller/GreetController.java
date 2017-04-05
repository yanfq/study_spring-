package yanfq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yanfq.domain.Greet;
import yanfq.service.GreetService;

import java.util.List;

/**
 * Created by yanfq on 2017/3/23.
 */
@RestController
public class GreetController {

    @Autowired
    private GreetService greetService;
    @RequestMapping("/greet")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        greetService.addInfo(name);
        List<Greet> greetList = greetService.getInfo(name);
        StringBuilder sb = new StringBuilder();
        for(Greet greet:greetList){
            sb.append(greet.getName()).append(",").append(greet.getContent()).append("<br>");
        }
        return sb.toString();
    }


}
