package com.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  @Autowired
  private RabbitProperties rabbitProperties;

  @Bean
  public Queue commentNotificationQueue() throws Exception {
    try {
      String host = rabbitProperties.determineHost();
      int port = rabbitProperties.determinePort();
      String username = rabbitProperties.determineUsername();
      String password = rabbitProperties.determinePassword();
      
      System.out.println("Host: " + host);
      System.out.println("Port: " + port);
      System.out.println("Username: " + username);
      System.out.println("Password: " + password);
      
      // Queue(name, durable, exclusive, autoDelete)
      Queue q = new Queue("Queue created by Spring Boot", true, false, false);

      return q;
    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }
}
