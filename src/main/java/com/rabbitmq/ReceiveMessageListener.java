package com.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveMessageListener {
  @RabbitListener(queues = "tpu.queue")
  public void receive(String message) {
    System.out.println(message);
  }
}
