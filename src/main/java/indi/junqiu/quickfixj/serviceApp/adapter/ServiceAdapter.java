package indi.junqiu.quickfixj.serviceApp.adapter;

import lombok.extern.slf4j.Slf4j;
import quickfix.*;
import quickfix.field.MsgType;
import quickfix.field.Password;
import quickfix.field.Username;

/**
 * @program: ServiceAdapter
 * @description: ServiceAdapter 登录校验
 * @author: Lorin
 * @create: 2023-07-12 10:24
 **/
@Slf4j
public class ServiceAdapter extends MessageCracker implements Application {

    @Override
    public void onCreate(SessionID sessionId) {
    }

    @Override
    public void onLogon(SessionID sessionId) {
        log.info("onLogon: SessionId={}", sessionId);
    }

    @Override
    public void onLogout(SessionID sessionId) {
    }

    @Override
    public void toApp(Message message, SessionID sessionId) throws DoNotSend {
    }

    @Override
    public void fromApp(Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        log.info("fromApp: Message={}, SessionId={}", message, sessionId);
        try {
            // 使用 MessageCracker 分解不同的消息
            crack(message, sessionId);
        } catch (UnsupportedMessageType | IncorrectTagValue e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void toAdmin(Message message, SessionID sessionId) {

    }

    @Override
    public void fromAdmin(Message message, SessionID sessionId) throws RejectLogon, FieldNotFound {
        // 登录操作鉴权逻辑
        if (message.getHeader().getString(MsgType.FIELD).equals(MsgType.LOGON)) {
            log.info("fromAdmin logon: Message={}, SessionId={}", message, sessionId);

            String username = message.getString(Username.FIELD);
            String password = message.getString(Password.FIELD);
            if (!"username".equals(username) || !"password".equals(password)) {
                log.error("登录信息验证异常：{} {} {}", sessionId, username, password);
                throw new RejectLogon("Invalid username or password");
            } else {
                log.info("登录成功：{}", sessionId);
            }
        } else {
            log.info("fromAdmin: Message={}, SessionId={}", message, sessionId);
            try {
                // 使用 MessageCracker 分解不同的消息
                crack(message, sessionId);
            } catch (UnsupportedMessageType | IncorrectTagValue e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void onMessage(Message message, SessionID sessionID) throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
        log.info("Message={}, SessionId={}", message, sessionID);
    }

    /*Fix4.1 MessageCracker*/
    @MessageCracker.Handler
    public void onMessage(quickfix.fix41.Logon message, SessionID sessionID) throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
        log.info("Message={}, SessionId={}", message, sessionID);
    }

    @MessageCracker.Handler
    public void onMessage(quickfix.fix41.Heartbeat message, SessionID sessionID) throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
        log.info("Message={}, SessionId={}", message, sessionID);
    }

    @MessageCracker.Handler
    public void orderCancelRequestHandler(quickfix.fix41.OrderCancelRequest orderCancelRequest, SessionID sessionID) {
        log.info("Message={}, SessionId={}", orderCancelRequest, sessionID);
    }

    /*Fix4.2 MessageCracker*/
    @MessageCracker.Handler
    public void onMessage(quickfix.fix42.Logon message, SessionID sessionID) throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
        log.info("Message={}, SessionId={}", message, sessionID);
    }

    @MessageCracker.Handler
    public void onMessage(quickfix.fix42.Heartbeat message, SessionID sessionID) throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
        log.info("Message={}, SessionId={}", message, sessionID);
    }

    @MessageCracker.Handler
    public void orderCancelRequestHandler(quickfix.fix42.OrderCancelRequest orderCancelRequest, SessionID sessionID) {
        log.info("Message={}, SessionId={}", orderCancelRequest, sessionID);
    }
}
