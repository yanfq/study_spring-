package yanfq.mqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by yanfq on 17-4-26.
 */
@SpringBootApplication
public class PublishApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PublishApplication.class, args);
        Publish publish = context.getBean("publish", Publish.class);
        try {
            for(int i = 0 ; i<5 ; i++){
                publish.sendMsg("测试Spring MQTT发送消息"+i);
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        context.close();
    }

}
