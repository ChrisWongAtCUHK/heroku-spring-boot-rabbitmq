package com.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.model.UserMessage;

@RestController
@RequestMapping("/rabbitmq")
@CrossOrigin(origins = "*") // 允許所有來源的跨域請求
public class RabbitMQController {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @GetMapping("/sendTopic")
  public String sendTopic(@RequestParam String routingKey, @RequestParam String name, @RequestParam String msg,
      @RequestHeader(value = "X-Send-Time", required = false) String sendTime) {
    UserMessage userMsg = new UserMessage(name, msg);
    System.out.println("收到訊息: " + msg);
    System.out.println("Dioxus 發送時間: " + sendTime);
    // 這裡的 routingKey 是關鍵
    rabbitTemplate.convertAndSend("my.topic.exchange", routingKey, userMsg);
    return "Topic Message Sent with key: " + routingKey;
  }
}
