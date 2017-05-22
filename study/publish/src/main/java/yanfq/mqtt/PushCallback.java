package yanfq.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by yanfq on 17-4-25.
 */
public class PushCallback implements MqttCallback {
    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("连接断开...");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息内容 : " + new String(message.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("deliveryComplete---------" + iMqttDeliveryToken.isComplete());
    }
}
