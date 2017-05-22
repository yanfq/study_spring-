package yanfq.conf;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.concurrent.TimeUnit;

/**
 * Created by yanfq on 17-4-26.
 */

@Configuration
public class EmbeddedServletConf {
    /**
     * 设置Spring boot内嵌tomcat启动端口
     * 便于同时启动多个应用调试
     * @return
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer(){
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setPort(8088);
        factory.setSessionTimeout(10, TimeUnit.MINUTES);
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/notfound.html"));
        return factory;
    }
}
