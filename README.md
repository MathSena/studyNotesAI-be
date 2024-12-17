# Study Notes App

Este projeto consiste em uma aplicação que gera **notas de estudo** com base em tópicos fornecidos pelos usuários. O *
*back-end** foi desenvolvido em **Spring Boot**, que consome a API do **ChatGPT** para gerar as notas. O **front-end**
foi desenvolvido em **Angular** e exibe as informações geradas pelo back-end de forma simples e interativa.

---

## Tecnologias Utilizadas

### Back-end:

- **Spring Boot**: Framework principal para desenvolvimento.
- **Spring WebFlux**: Permite chamadas assíncronas e reativas.
- **OpenAI API**: Utilizada para gerar notas de estudo com base nos tópicos fornecidos.
- **Swagger UI**: Documentação interativa e teste da API.

### Front-end:

- **Angular**: Framework utilizado para criar o front-end.
- **Tailwind CSS**: Framework de estilização para componentes responsivos e modernos.

### Deploy:

- **AWS Elastic Beanstalk**: Deploy do back-end.
- **AWS S3 ou AWS Amplify**: Deploy do front-end.

---

## Como Rodar o Projeto

### Back-end:

1. **Clone o repositório do back-end**:
   ```bash
   git clone <URL_DO_REPOSITORIO_BACKEND>
   cd backend
   ```

2. **Configure a chave da API do ChatGPT**:
    - No arquivo `application.properties`, adicione sua chave da OpenAI:
      ```properties
      openai.api.key=YOUR_API_KEY
      ```

3. **Instale as dependências e execute o projeto**:
   ```bash
   mvn install
   mvn spring-boot:run
   ```

4. **Acesse a documentação da API no Swagger**:
    - A documentação estará disponível em:  
      `http://localhost:8080/swagger-ui.html`.

---

### Front-end:

1. **Clone o repositório do front-end**:
   ```bash
   git clone <URL_DO_REPOSITORIO_FRONTEND>
   cd frontend
   ```

2. **Instale as dependências**:
   ```bash
   npm install
   ```

3. **Execute o front-end**:
   ```bash
   npm start
   ```

4. **Acesse a aplicação no navegador**:
    - A aplicação estará disponível em:  
      `http://localhost:4200`.

## Funcionalidades

- **Entrada de tópico**: O usuário insere um tópico no campo de texto.
- **Geração de notas**: As notas de estudo são geradas em tempo real pela API do ChatGPT.
- **Validação**: O formulário valida entradas vazias e exibe mensagens de erro.
- **Feedback visual**: Indicações visuais de carregamento, erros ou sucesso na interface.
- **Documentação Swagger**: A API é documentada e pode ser testada via Swagger.

---

## Documentação da API

A API foi documentada utilizando **Swagger UI**. Acesse a documentação interativa em:

```
http://localhost:8080/swagger-ui.html
```

A documentação inclui:

- Endpoints disponíveis.
- Parâmetros aceitos.
- Exemplos de respostas.

---

## Licença

Este projeto é licenciado sob a **MIT License** - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## Contato

Caso tenha dúvidas ou sugestões, entre em contato pelo e-mail:  
[mathsenajp@gmail.com](mailto:mathsenajp@gmail.com)