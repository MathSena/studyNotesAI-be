package com.mathsena.studynotesai.controller;

import com.mathsena.studynotesai.models.TopicRequest;
import com.mathsena.studynotesai.models.TopicResponse;
import com.mathsena.studynotesai.services.OpenAiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/topics")
@Validated // Validação de bean
public class TopicsController {

  @Autowired private OpenAiService openAiService;

  /**
   * Endpoint para sugerir tópicos de estudo com base em um tema.
   *
   * @param topicRequest Objeto JSON recebido com o tema `topic`.
   * @return Lista de tópicos sequenciais (fundamentos até avançados)
   */
  @Operation(
      summary = "Sugere tópicos de estudo",
      description =
          "Recebe um tema e retorna uma lista de tópicos sequenciais, do básico ao avançado, para estudo.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Tópicos sugeridos com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro: Tema vazio ou inválido"),
        @ApiResponse(responseCode = "500", description = "Erro ao processar a solicitação")
      })
  @PostMapping
  public ResponseEntity<TopicResponse> getStudyTopics(
      @RequestBody @Validated @Parameter(description = "Tema para sugerir tópicos de estudo")
          TopicRequest topicRequest) {

    String topic = topicRequest.getTopic();
    try {
      List<String> topics = openAiService.getStudyTopics(topic);
      return ResponseEntity.ok(new TopicResponse(topics));
    } catch (Exception e) {
      return ResponseEntity.status(500)
          .body(new TopicResponse(List.of("Erro ao buscar tópicos: " + e.getMessage())));
    }
  }
}
