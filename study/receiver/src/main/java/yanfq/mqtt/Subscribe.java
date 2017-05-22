package yanfq.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yanfq on 17-4-26.
 */

@Component
public class Subscribe {
    @Autowired
    MqttClient subscribeClient;

    @Autowired
    MqttTopic subscribeTopic;

    @Autowired
    MqttConnectOptions mqttConnectOptions;

    public void subcribeInit(){
        int[] Qos = {1};
        String TOPIC = "mqtt_topic";
        String[] topic = {TOPIC};
        try{
            mqttConnectOptions.setWill(subscribeTopic, "close".getBytes(), 1, true);
            subscribeClient.setCallback(new PushCallback());
            subscribeClient.subscribe(topic, Qos);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
