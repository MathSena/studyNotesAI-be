package com.mathsena.studynotesai.models;

import jakarta.validation.constraints.NotEmpty;

public class TopicRequest {

  @NotEmpty(message = "O tema não pode estar vazio.")
  private String topic;

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }
}
