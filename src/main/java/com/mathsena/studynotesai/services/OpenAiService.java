package com.mathsena.studynotesai.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenAiService {

  private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
  private static final String MODEL = "gpt-3.5-turbo";
  private static final String CONTENT_TYPE_VALUE = "application/json";

  @Value("${openai.api.key}")
  private String apiKey;

  public List<String> getStudyTopics(String topic) {
    try {
      HttpURLConnection connection = setupConnection();
      String requestBody = createRequestBody(topic);

      sendRequest(connection, requestBody);

      int responseCode = connection.getResponseCode();
      if (responseCode != HttpURLConnection.HTTP_OK) {
        throw new ApiRequestException("Erro na API OpenAI (Código HTTP: " + responseCode + ")");
      }

      String response = getResponse(connection);
      return extractTopicsFromResponse(response);
    } catch (Exception e) {
      throw new ApiRequestException("Erro ao comunicar com a API OpenAI: " + e.getMessage(), e);
    }
  }

  private HttpURLConnection setupConnection() throws Exception {
    URL url = new URL(OPENAI_API_URL);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Authorization", "Bearer " + apiKey);
    connection.setRequestProperty("Content-Type", CONTENT_TYPE_VALUE);
    connection.setDoOutput(true);
    return connection;
  }

  private String createRequestBody(String topic) {
    String prompt =
        String.format(
            "Liste passo a passo, de forma sequencial e lógica, os tópicos que uma pessoa deve estudar para se tornar proficiente em '%s'. Inclua fundamentos, tópicos intermediários e avançados.",
            topic);
    return String.format(
        "{\"model\": \"%s\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}",
        MODEL, prompt);
  }

  private void sendRequest(HttpURLConnection connection, String requestBody) throws Exception {
    try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
      writer.write(requestBody);
      writer.flush();
    }
  }

  private String getResponse(HttpURLConnection connection) throws Exception {
    try (BufferedReader reader =
        new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
      StringBuilder response = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        response.append(line);
      }
      return response.toString();
    }
  }

  private List<String> extractTopicsFromResponse(String response) {
    try {
      JsonNode root = new ObjectMapper().readTree(response);
      String responseContent = root.path("choices").get(0).path("message").path("content").asText();

      List<String> topics = new ArrayList<>();
      for (String line : responseContent.split("\n")) {
        if (!line.trim().isEmpty()) {
          topics.add(line.trim());
        }
      }
      return topics;
    } catch (Exception e) {
      throw new ApiRequestException(
          "Erro ao processar a resposta da API OpenAI: " + e.getMessage(), e);
    }
  }

  // Custom Exception for API Errors
  public static class ApiRequestException extends RuntimeException {
    public ApiRequestException(String message) {
      super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
      super(message, cause);
    }
  }
}
