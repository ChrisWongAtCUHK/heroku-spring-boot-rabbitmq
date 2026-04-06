package com.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  @Bean
  public Queue commentNotificationQueue() throws Exception {
    try {
      // Queue(name, durable, exclusive, autoDelete)
      return new Queue("tpu.queue");
    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }
}
