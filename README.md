# Study Notes App

Este projeto consiste em uma API que gera notas de estudo com base em tópicos fornecidos pelos usuários. O back-end é
desenvolvido em Spring Boot e consome a API do ChatGPT para gerar as notas. O front-end é desenvolvido em React.js e
interage com a API para exibir as notas geradas.

## Tecnologias Utilizadas:

- **Back-end**: Spring Boot, WebFlux, OpenAI API
- **Front-end**: React.js, Material-UI
- **Deploy**: AWS (Elastic Beanstalk para o back-end, S3 ou Amplify para o front-end)
- **Documentação**: Swagger UI

## Como Rodar o Projeto:

### Back-end:

1. Clone o repositório do back-end.
2. Instale as dependências com `mvn install` (caso use Maven).
3. Configure a chave da API do ChatGPT no ambiente.
4. Rode a aplicação com `mvn spring-boot:run`.

### Front-end:

1. Clone o repositório do front-end.
2. Instale as dependências com `npm install`.
3. Rode o front-end com `npm start`.

## Licença:

Este projeto é licenciado sob a MIT License - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
