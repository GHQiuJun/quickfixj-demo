package indi.junqiu.quickfixj.config;

import indi.junqiu.quickfixj.clientApp.adapter.ClientAdapter;
import indi.junqiu.quickfixj.serviceApp.adapter.ServiceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import quickfix.*;

/**
 * @program: Config
 * @description: 配置类 自定义配置 Service Client
 * @author: Lorin
 * @create: 2023-07-12 18:58
 **/
@Configuration
public class AppConfig {

    @Bean
    public SessionSettings settings() {
        return new SessionSettings();
    }

    @Bean
    public MessageFactory messageFactory() {
        return new DefaultMessageFactory();
    }

    @Bean
    public LogFactory logFactory(SessionSettings settings) {
        return new FileLogFactory(settings);
    }

    @Bean
    public ClientAdapter clientApplication() {
        return new ClientAdapter();
    }

    @Bean
    public MessageStoreFactory messageStoreFactory() {
        return new MemoryStoreFactory();
    }

    @Bean
    public Initiator clientInitiator(ClientAdapter clientApplication, MessageStoreFactory messageStoreFactory,
                                     SessionSettings clientSessionSettings, LogFactory clientLogFactory,
                                     MessageFactory clientMessageFactory) throws ConfigError {

        return new ThreadedSocketInitiator(clientApplication, messageStoreFactory, clientSessionSettings,
                clientLogFactory, clientMessageFactory);
    }

    // Acceptor configuration
    @Bean
    public ServiceAdapter serviceAdapter() {
        return new ServiceAdapter();
    }

    @Bean
    public Acceptor serverAcceptor(ServiceAdapter serverApplication, MessageStoreFactory serverMessageStoreFactory,
                                   SessionSettings serverSessionSettings, LogFactory serverLogFactory,
                                   MessageFactory serverMessageFactory) throws ConfigError {

        return new ThreadedSocketAcceptor(serverApplication, serverMessageStoreFactory, serverSessionSettings,
                serverLogFactory, serverMessageFactory);
    }
}


