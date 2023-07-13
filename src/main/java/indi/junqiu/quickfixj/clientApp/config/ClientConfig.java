package indi.junqiu.quickfixj.clientApp.config;

import indi.junqiu.quickfixj.clientApp.adapter.ClientAdapter;
import indi.junqiu.quickfixj.serviceApp.adapter.ServiceAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import quickfix.*;

/**
 * @program: ClientConfig
 * @description: 配置类 自定义配置 Client
 * @author: Lorin
 * @create: 2023-07-12 18:58
 **/
@Configuration
@ConditionalOnProperty(name = {"quickfixj.client.enabled"}, havingValue = "true")
public class ClientConfig {

    @Bean
    public MessageStoreFactory messageStoreFactory(SessionSettings clientSessionSettings) {
        return new FileStoreFactory(clientSessionSettings);
    }

    @Bean
    public ClientAdapter clientApplication() {
        return new ClientAdapter();
    }

    @Bean
    public Initiator clientInitiator(ClientAdapter clientApplication, MessageStoreFactory clientMessageStoreFactory,
                                     SessionSettings clientSessionSettings, LogFactory clientLogFactory,
                                     MessageFactory clientMessageFactory) throws ConfigError {

        return new ThreadedSocketInitiator(clientApplication, clientMessageStoreFactory, clientSessionSettings,
                clientLogFactory, clientMessageFactory);
    }
}


