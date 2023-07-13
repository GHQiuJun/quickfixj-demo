package indi.junqiu.quickfixj.serviceApp.config;

import indi.junqiu.quickfixj.serviceApp.adapter.ServiceAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import quickfix.*;

/**
 * @program: ServiceConfig
 * @description: 配置类 自定义配置 Service
 * @author: Lorin
 * @create: 2023-07-12 18:58
 **/
@Configuration
@ConditionalOnProperty(name = {"quickfixj.server.enabled"}, havingValue = "true")
public class ServiceConfig {
    @Bean
    public ServiceAdapter serverApplication() {
        return new ServiceAdapter();
    }

    @Bean
    public Acceptor serverAcceptor(ServiceAdapter serverApplication, MessageStoreFactory serverMessageStoreFactory, SessionSettings serverSessionSettings,
                                   LogFactory serverLogFactory, MessageFactory serverMessageFactory) throws ConfigError {

        return new ThreadedSocketAcceptor(serverApplication, serverMessageStoreFactory, serverSessionSettings,
                serverLogFactory, serverMessageFactory);
    }
}


