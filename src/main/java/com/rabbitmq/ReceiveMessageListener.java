package com.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.model.UserMessage;

@Component
public class ReceiveMessageListener {
  // 監聽 A 隊列
  @RabbitListener(queues = "queue.A", containerFactory = "rabbitListenerContainerFactory")
  public void receiveA(UserMessage userMsg) {
    System.out.println(" [Receiver A] 收到廣播: " + userMsg.getName());
  }

  // 監聽 B 隊列
  @RabbitListener(queues = "queue.B", containerFactory = "rabbitListenerContainerFactory")
  public void receiveB(UserMessage userMsg) {
    System.out.println(" [Receiver B] 收到廣播: " + userMsg.getName());
  }
}
