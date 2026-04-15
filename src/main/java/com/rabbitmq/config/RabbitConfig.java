package com.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  // 1. 定義 Fanout 交換機
  @Bean
  public FanoutExchange fanoutExchange() {
    return new FanoutExchange("fanout.exchange");
  }

  // 2. 定義兩個不同的 Queue
  @Bean
  public Queue queueA() {
    return new Queue("queue.A");
  }

  @Bean
  public Queue queueB() {
    return new Queue("queue.B");
  }

  // 3. 將兩個 Queue 都綁定到同一個 Fanout 交換機
  @Bean
  public Binding bindingA(Queue queueA, FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(queueA).to(fanoutExchange);
  }

  @Bean
  public Binding bindingB(Queue queueB, FanoutExchange fanoutExchange) {
    return BindingBuilder.bind(queueB).to(fanoutExchange);
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
    converter.setAlwaysConvertToInferredType(true);
    return converter;
  }

  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setMessageConverter(jsonMessageConverter());
    return factory;
  }
}