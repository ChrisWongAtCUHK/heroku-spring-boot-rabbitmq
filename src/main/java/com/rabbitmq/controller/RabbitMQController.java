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

  @GetMapping("/sendUser")
  public String sendUser(String name, String msg) {
    UserMessage userMsg = new UserMessage(name, msg);
    // convertAndSend(交換機名稱, 路由鍵, 訊息物件)
    rabbitTemplate.convertAndSend("my-topic-exchange", "tpu.important.msg", userMsg);

    return "JSON Message Sent!";
  }
}
