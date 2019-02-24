package ru.zakharov.market.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import org.springframework.stereotype.Service;
import ru.zakharov.market.dto.ShopOrderTransaction;
import ru.zakharov.market.entities.ShopOrder;


// http://localhost:8161/admin

@Service
public class JmsService {
    private static final Logger logger = LoggerFactory.getLogger(JmsService.class);

    @Autowired
    private JmsTemplate jmsTemplate;

/*    public void send() {
        jmsTemplate.send("market", session -> {
            TextMessage jmsMessage = session.createTextMessage("Hello");
            logger.info(">>> Sending: " + jmsMessage.getText());
            return jmsMessage;
        });

        jmsTemplate.send("market.topic", session -> {
            TextMessage jmsMessage = session.createTextMessage("Hello Topic");
            logger.info(">>> Sending to topic: " + jmsMessage.getText());
            return jmsMessage;
        });

        //jmsTemplate.convertAndSend("market", new Product());
        //return "redirect:/";
    }*/

    public void sendShopOrder(ShopOrder shopOrder) {
        ShopOrderTransaction shopDto = new ShopOrderTransaction();
        shopDto.setOrderId(shopOrder.getId());
        shopDto.setCarItemList(shopOrder.getCartItemList());
        logger.info(">>> Sending to Queue: " + shopDto.toString());
        jmsTemplate.convertAndSend("market.queue", shopDto);
    }
}
