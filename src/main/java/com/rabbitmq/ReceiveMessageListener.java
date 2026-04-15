package com.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.model.UserMessage;

@Component
public class ReceiveMessageListener {
  @RabbitListener(queues = "tpu.queue", containerFactory = "rabbitListenerContainerFactory")
  public void receive(UserMessage userMsg) {
    System.out.println("成功收到 JSON！用戶：" + userMsg.getName());
  }
}
