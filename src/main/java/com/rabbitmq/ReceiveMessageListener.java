package com.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.model.UserMessage;

@Component
public class ReceiveMessageListener {
  @RabbitListener(queues = "queue.hk", containerFactory = "rabbitListenerContainerFactory")
  public void receiveHk(UserMessage userMsg) {
    System.out.println(" [HK Receiver] 收到地區訊息: " + userMsg.getContent());
  }

  @RabbitListener(queues = "queue.all.news", containerFactory = "rabbitListenerContainerFactory")
  public void receiveAllNews(UserMessage userMsg) {
    System.out.println(" [News Receiver] 收到新聞訊息: " + userMsg.getContent());
  }
}
