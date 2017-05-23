package yanfq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import yanfq.controller.TimerServer;

/**
 * Created by yanfq on 17-4-26.
 */
@SpringBootApplication
public class StartApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StartApplication.class, args);
        TimerServer timerServer = context.getBean("timerServer", TimerServer.class);
        timerServer.timerHandler(8080);
    }

}
