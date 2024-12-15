package com.mathsena.studynotesai.controller;

import com.mathsena.studynotesai.models.TopicRequest;
import com.mathsena.studynotesai.models.TopicResponse;
import com.mathsena.studynotesai.services.OpenAiService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/topics")
public class TopicsController {

  @Autowired private OpenAiService openAiService;

  /**
   * Endpoint para sugerir tópicos de estudo com base em um tema.
   *
   * @param topicRequest Objeto JSON recebido com o tema `topic`.
   * @return Lista de tópicos sequenciais (fundamentos até avançados)
   */
  @PostMapping
  public ResponseEntity<TopicResponse> getStudyTopics(@RequestBody TopicRequest topicRequest) {
    String topic = topicRequest.getTopic();
    if (topic == null || topic.isEmpty()) {
      return ResponseEntity.badRequest()
          .body(new TopicResponse(List.of("O tema não pode estar vazio.")));
    }

    try {
      List<String> topics = openAiService.getStudyTopics(topic);
      return ResponseEntity.ok(new TopicResponse(topics));
    } catch (Exception e) {
      return ResponseEntity.status(500)
          .body(new TopicResponse(List.of("Erro ao buscar tópicos: " + e.getMessage())));
    }
  }
}
