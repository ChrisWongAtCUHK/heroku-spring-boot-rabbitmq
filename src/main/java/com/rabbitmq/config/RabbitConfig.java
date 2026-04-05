package com.rabbitmq.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  @Bean
  public Queue commentNotificationQueue() throws Exception {
    try {
      // 1. Get current date and time
      LocalDateTime now = LocalDateTime.now();

      // 2. Define a pattern (e.g., "yyyy-MM-dd HH:mm:ss")
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

      // 3. Format into a string
      String formatDateTime = now.format(formatter);

      // Queue(name, durable, exclusive, autoDelete)
      return new Queue("Queue created by Spring Boot: " + formatDateTime, true, false, false);
    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }
}
