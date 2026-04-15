package com.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.model.UserMessage;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQController {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @GetMapping("/sendFanout")
  public String sendFanout(String name, String msg) {
    UserMessage userMsg = new UserMessage(name, msg);

    // 參數：Exchange名稱, RoutingKey(Fanout模式下會被忽略，傳空字串即可), 訊息物件
    rabbitTemplate.convertAndSend("fanout.exchange", "", userMsg);

    return "Fanout Message Broadcasted!";
  }
}
