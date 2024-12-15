package com.mathsena.studynotesai;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudyNotesAiBeApplication {

  public static void main(String[] args) {
    // Carrega o arquivo .env
    Dotenv dotenv =
        Dotenv.configure()
            .ignoreIfMissing() // Ignorar se o .env não estiver presente
            .load();

    // Carregar variáveis essenciais
    String openAiApiKey = dotenv.get("OPENAI_API_KEY");
    String springAppName = dotenv.get("SPRING_APPLICATION_NAME");

    if (springAppName == null || springAppName.isBlank()) {
      throw new IllegalStateException(
          "A variável SPRING_APPLICATION_NAME não foi definida no arquivo .env");
    }
    if (openAiApiKey == null || openAiApiKey.isBlank()) {
      throw new IllegalStateException("A variável OPENAI_API_KEY não foi definida no arquivo .env");
    }

    // Define as variáveis no sistema para o Spring Boot
    System.setProperty("OPENAI_API_KEY", openAiApiKey);
    System.setProperty("SPRING_APPLICATION_NAME", springAppName);

    SpringApplication.run(StudyNotesAiBeApplication.class, args);
  }
}
