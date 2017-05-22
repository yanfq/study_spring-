package yanfq.mqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by yanfq on 17-4-26.
 */
@SpringBootApplication
public class ReceiverApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ReceiverApplication.class, args);
        Subscribe subscribe = context.getBean("subscribe", Subscribe.class);
        subscribe.subcribeInit();
        context.close();
    }

}
