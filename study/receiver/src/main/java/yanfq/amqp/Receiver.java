package yanfq.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by yanfq on 17-4-26.
 */
@Component
public class Receiver {

    @RabbitListener(queues = "queue1")
    public void queue1Message(Message message) {
        byte[] body = message.getBody();
        System.out.println("queue1收到消息: '" + new String(body) + "'");
    }

    @RabbitListener(queues = "queue2")
    public void queue2Message(Message message) {
        byte[] body = message.getBody();
        System.out.println("queue2收到消息: '" + new String(body) + "'");
    }
}
