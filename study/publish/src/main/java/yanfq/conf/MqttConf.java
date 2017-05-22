package yanfq.conf;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yanfq.mqtt.PushCallback;

/**
 * Created by yanfq on 17-4-26.
 */
@Configuration
public class MqttConf {

    @Bean
    MqttClient publishClient(MqttConnectOptions mqttConnectOptions){
        String HOST = "tcp://192.168.0.65:1883";
        String clientId = "publishServer";
        MqttClient client = null;
        try {
            client = new MqttClient(HOST, clientId, new MemoryPersistence());
            client.setCallback(new PushCallback());
            client.connect(mqttConnectOptions);
        }catch (Exception e){
            e.printStackTrace();
        }
        return client;
    }


    @Bean
    MqttConnectOptions mqttConnectOptions(){
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        return options;
    }
    
    @Bean
    MqttTopic publishTopic(MqttClient publishClient){
        String TOPIC = "mqtt_topic";
        MqttTopic topic = publishClient.getTopic(TOPIC);
        return topic;
    }

    @Bean
    MqttMessage message(){
        MqttMessage message = new MqttMessage();
        message.setQos(1);
        message.setRetained(true);
        return message;
    }
}
