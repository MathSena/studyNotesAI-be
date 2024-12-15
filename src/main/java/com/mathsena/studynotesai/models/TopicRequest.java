package com.mathsena.studynotesai.models;

public class TopicRequest {
  private String topic;

  public TopicRequest() {}

  public TopicRequest(String topic) {
    this.topic = topic;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }
}
