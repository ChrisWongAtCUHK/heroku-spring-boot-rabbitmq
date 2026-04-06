package com.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQController {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @GetMapping
  public String send(String message) {
    try {
      // message should not be null
      rabbitTemplate.convertAndSend("tpu.queue", message);
      return rabbitTemplate.getEncoding();
    } catch (Exception ex) {
      return ex.getMessage();
    }
  }
}
