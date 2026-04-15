package com.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {

  @RabbitListener(queues = "tpu.queue")
  public void receive(String message) {
    System.out.println("收到訊息: " + message);
  }
}