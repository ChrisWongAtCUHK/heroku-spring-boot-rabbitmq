package com.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange("my.topic.exchange");
  }

  @Bean
  public Queue hkQueue() {
    return new Queue("queue.hk"); // 專收香港相關
  }

  @Bean
  public Queue allNewsQueue() {
    return new Queue("queue.all.news"); // 專收所有新聞
  }

  // 綁定規則 1：只要 Routing Key 以 "hk." 開頭的，都進 hkQueue
  @Bean
  public Binding bindingHk(Queue hkQueue, TopicExchange topicExchange) {
    return BindingBuilder.bind(hkQueue).to(topicExchange).with("hk.#");
  }

  // 綁定規則 2：只要 Routing Key 以 ".news" 結尾的，都進 allNewsQueue
  @Bean
  public Binding bindingAllNews(Queue allNewsQueue, TopicExchange topicExchange) {
    return BindingBuilder.bind(allNewsQueue).to(topicExchange).with("*.news");
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