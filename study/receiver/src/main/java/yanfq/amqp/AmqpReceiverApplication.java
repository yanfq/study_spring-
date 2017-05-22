package yanfq.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by yanfq on 17-4-26.
 */
@SpringBootApplication
public class AmqpReceiverApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AmqpReceiverApplication.class, args);
        //context.close();
    }

}
