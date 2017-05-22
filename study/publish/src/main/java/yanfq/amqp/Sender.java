package yanfq.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yanfq on 17-4-26.
 */
@Component
public class Sender {
    @Autowired
    private RabbitTemplate myExchangeTemplate;

    public void sendMsg(String content) {
        myExchangeTemplate.convertAndSend(content);
        System.out.println("发送消息: '" + content + "'");
    }

}
