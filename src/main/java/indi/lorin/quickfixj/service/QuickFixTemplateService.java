package indi.lorin.quickfixj.service;

import io.allune.quickfixj.spring.boot.starter.template.QuickFixJTemplate;
import org.springframework.stereotype.Service;
import quickfix.Acceptor;
import quickfix.Initiator;
import quickfix.Message;
import quickfix.SessionID;

/**
 * @program: quickFixTemplateService
 * @description: quickFixTemplateService
 * @author: Lorin
 * @create: 2023-07-11 17:53
 **/
@Service
public class QuickFixTemplateService {
    private final QuickFixJTemplate quickFixJTemplate;

    private final Initiator clientInitiator;

    private final Acceptor acceptor;

    public QuickFixTemplateService(QuickFixJTemplate quickFixJTemplate, Initiator clientInitiator, Acceptor acceptor) {
        this.quickFixJTemplate = quickFixJTemplate;
        this.clientInitiator = clientInitiator;
        this.acceptor = acceptor;
    }

    /**
     * 根据 fixVersion 发送 message to Service
     *
     * @param fixVersion
     * @param message
     */
    public void sendService(String fixVersion, Message message) {
        SessionID sessionId = clientInitiator.getSessions().stream()
                .filter(id -> id.getBeginString().equals(fixVersion))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        quickFixJTemplate.send(message, sessionId);
    }

    /**
     * 根据 fixVersion 发送 message to Client
     *
     * @param fixVersion
     * @param message
     */
    public void sendClient(String fixVersion, Message message) {
        SessionID sessionId = acceptor.getSessions().stream()
                .filter(id -> id.getBeginString().equals(fixVersion))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        quickFixJTemplate.send(message, sessionId);
    }
}
