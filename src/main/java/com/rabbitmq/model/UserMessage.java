package com.rabbitmq.model;

import java.io.Serializable;

// 必須實作 Serializable 或是使用 JSON 轉換
public class UserMessage implements Serializable {
  private String name;
  private String content;

  // 必須有空構造函數、Getter 和 Setter
  public UserMessage() {
  }

  public UserMessage(String name, String content) {
    this.name = name;
    this.content = content;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
