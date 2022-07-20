package io.laokou.admin.infrastructure.component.listener;
import io.laokou.admin.application.service.SysMessageApplicationService;
import io.laokou.admin.infrastructure.component.event.PushMessageEvent;
import io.laokou.admin.infrastructure.component.event.SaveMessageEvent;
import io.laokou.admin.infrastructure.component.event.SendMessageEvent;
import io.laokou.admin.interfaces.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
@Component
@ConditionalOnWebApplication
public class MessageListener {

    @Autowired
    private SysMessageApplicationService sysMessageApplicationService;

    @Async
    @Order
    @EventListener(value = SaveMessageEvent.class)
    public void listenSave(SaveMessageEvent event) {
        MessageDTO dto = (MessageDTO) event.getSource();
        sysMessageApplicationService.insertMessage(dto);
    }

    @Async
    @Order
    @EventListener(value = PushMessageEvent.class)
    public void listenPush(PushMessageEvent event) {
        MessageDTO dto = (MessageDTO) event.getSource();
        sysMessageApplicationService.pushMessage(dto);
    }

    @Async
    @Order
    @EventListener(value = SendMessageEvent.class)
    public void listenSend(SendMessageEvent event) {
        MessageDTO dto = (MessageDTO) event.getSource();
        sysMessageApplicationService.consumeMessage(dto);
    }

}