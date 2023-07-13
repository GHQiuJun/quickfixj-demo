package indi.lorin.quickfixj.clientApp;

import indi.lorin.quickfixj.enums.FixVersionEnum;
import indi.lorin.quickfixj.service.QuickFixTemplateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import quickfix.field.*;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.OK;

/**
 * @program: controller
 * @description: Client Controller
 * @author: Lorin
 * @create: 2023-07-11 17:41
 **/
@RestController
public class Controller {

    private final QuickFixTemplateService quickFixTemplateService;

    public Controller(QuickFixTemplateService quickFixTemplateService) {
        this.quickFixTemplateService = quickFixTemplateService;
    }

    @RequestMapping("/send-server-message-4.1")
    @ResponseStatus(OK)
    public void sendMessageToServer41(@RequestParam String body) {
        quickfix.fix41.Message message = new quickfix.fix41.OrderCancelRequest(
                new OrigClOrdID(body),
                new ClOrdID(body),
                new Symbol(body),
                new Side(Side.BUY));
        quickFixTemplateService.sendService(FixVersionEnum.FIX_VERSION_4_1.getCode(), message);
    }

    @RequestMapping("/send-server-message-4.2")
    @ResponseStatus(OK)
    public void sendMessageToServer42(@RequestParam String body) {
        quickfix.fix42.Message message = new quickfix.fix42.OrderCancelRequest(
                new OrigClOrdID(body),
                new ClOrdID(body),
                new Symbol(body),
                new Side(Side.BUY), new TransactTime(LocalDateTime.now()));
        quickFixTemplateService.sendService(FixVersionEnum.FIX_VERSION_4_2.getCode(), message);
    }
}
