package indi.junqiu.quickfixj.clientApp.adapter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import quickfix.*;
import quickfix.field.MsgType;
import quickfix.field.Password;
import quickfix.field.SenderCompID;
import quickfix.field.Username;

/**
 * @program: ClientAdapter
 * @description: ClientAdapter 增加登录校验
 * @author: Lorin
 * @create: 2023-07-12 10:24
 **/
@Slf4j
public class ClientAdapter extends ApplicationAdapter {

    @SneakyThrows
    @Override
    public void toAdmin(Message message, SessionID sessionId) {
        if (message.getHeader().getString(MsgType.FIELD).equals(MsgType.LOGON)) {
            addAuthentication(message);
            log.info("toAdmin logon: Message={}, SessionId={}", message, sessionId);
        } else {
            log.info("toAdmin: Message={}, SessionId={}", message, sessionId);
        }
        super.toAdmin(message, sessionId);
    }

    private void addAuthentication(Message message) {
        // Add other necessary fields for authentication
        message.setField(new Username("username"));
        message.setField(new Password("password"));
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionId) throws RejectLogon, IncorrectTagValue, IncorrectDataFormat, FieldNotFound {
        if (message.getHeader().getString(MsgType.FIELD).equals(MsgType.LOGON) && message.getHeader().getString(SenderCompID.FIELD).contains("Client")) {
            log.info("fromAdmin logon: Message={}, SessionId={}", message, sessionId);

            String username = message.getString(Username.FIELD);
            String password = message.getString(Password.FIELD);
            if (!"username".equals(username) || !"password".equals(password)) {
                log.error("登录信息验证异常：{} {}", username, password);
                throw new RejectLogon("Invalid username or password");
            }
        } else {
            log.info("fromAdmin: Message={}, SessionId={}", message, sessionId);
        }
        super.fromAdmin(message, sessionId);
    }
}
