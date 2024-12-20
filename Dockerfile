# Etapa 1: Construir a aplicação usando Maven
FROM maven:3.8.4-openjdk-17-slim AS builder

# Defina o diretório de trabalho no container
WORKDIR /app

# Copie os arquivos pom.xml e baixe as dependências (cache de dependências)
COPY pom.xml .

RUN mvn dependency:go-offline

# Copie o código-fonte da aplicação
COPY src /app/src

# Compile a aplicação (pode ajustar o comando se precisar de configurações específicas)
RUN mvn clean package -DskipTests

# Etapa 2: Criar o container de execução
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho no container
WORKDIR /app

# Copie o arquivo JAR gerado pela etapa anterior
COPY --from=builder /app/target/studynotesAi-0.0.1-SNAPSHOT.jar /app/studynotesAi.jar

# Exponha a porta em que a aplicação Spring Boot irá rodar (default 8080)
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "/app/studynotesAi.jar"]
