package yanfq.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by yanfq on 17-4-26.
 */
@SpringBootApplication
public class AmqpPublishApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AmqpPublishApplication.class, args);
        Sender sender = context.getBean("sender", Sender.class);
        try {
            for(int i = 0 ; i<5 ; i++){
                sender.sendMsg("测试Spring AMQP发送消息"+i);
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        context.close();
    }

}
