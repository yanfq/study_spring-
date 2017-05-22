package yanfq.conf;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yanfq on 17-4-26.
 */

@Configuration
public class AmqpConf {
    @Bean
    CachingConnectionFactory connectionFactory_65() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setHost("192.168.0.65");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        //connectionFactory.setPublisherConfirms(true); //必须要设置
        return connectionFactory;
    }

    @Bean
    Exchange myExchange() {
        return ExchangeBuilder.topicExchange("yanfq.topic").durable(true).build();
    }

    @Bean
    Queue queue1() {
        return QueueBuilder.durable("queue1").build();
    }

    @Bean
    Queue queue2() {
        return QueueBuilder.durable("queue2").build();
    }

    @Bean
    public Binding myExchangeBinding(@Qualifier("myExchange") Exchange topicExchange,
                                     @Qualifier("queue1") Queue queue1) {
        return BindingBuilder.bind(queue1).to(topicExchange).with("yanfq.#").noargs();
    }

    @Bean
    public Binding myExchangeBinding2(@Qualifier("myExchange") Exchange topicExchange,
                                     @Qualifier("queue2") Queue queue2) {
        return BindingBuilder.bind(queue2).to(topicExchange).with("yanfq.#").noargs();
    }

    @Bean
    public RabbitTemplate myExchangeTemplate(CachingConnectionFactory connectionFactory_65) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory_65);
        rabbitTemplate.setExchange("yanfq.topic");
        rabbitTemplate.setRoutingKey("yanfq.abc.123");
        return rabbitTemplate;
    }

    @Bean
    public RabbitTemplate myExchangeTemplate2(CachingConnectionFactory connectionFactory_65) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory_65);
        rabbitTemplate.setExchange("yanfq.topic");
        rabbitTemplate.setRoutingKey("yanfq.abc.123");
        return rabbitTemplate;
    }
}
