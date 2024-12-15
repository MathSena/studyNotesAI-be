package com.mathsena.studynotesai.models;

import java.util.List;

public class TopicResponse {
  private List<String> topics;

  public TopicResponse() {}

  public TopicResponse(List<String> topics) {
    this.topics = topics;
  }

  public List<String> getTopics() {
    return topics;
  }

  public void setTopics(List<String> topics) {
    this.topics = topics;
  }
}
