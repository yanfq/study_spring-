package yanfq.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yanfq on 17-4-26.
 */
@Component
public class Publish {

    @Autowired
    MqttClient publishClient;

    @Autowired
    MqttTopic publishTopic;

    @Autowired
    MqttMessage message;

    public void sendMsg(String msg){
        message.setPayload(msg.getBytes());
        try {
            MqttDeliveryToken token = publishTopic.publish(message);
            token.waitForCompletion();
            System.out.println("message is published: " + token.isComplete());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
