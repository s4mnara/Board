# Board Manager

## Descrição

Aplicação web para gerenciamento de boards (quadros), contendo colunas e cards (tarefas). Permite criar, editar, excluir e listar boards, colunas e cards com interface interativa em frontend JavaScript e backend em Spring Boot com JPA e MySQL.

---

## Tecnologias Utilizadas

- Java 17+
- Spring Boot 3.x
- Spring Data JPA / Hibernate
- MySQL (ou outro banco relacional)
- Jakarta Persistence API (JPA)
- HTML, CSS, JavaScript (Vanilla JS + Fetch API)
- Maven (ou Gradle)
- Lombok (para getters/setters e construtores)

---

## Funcionalidades

- Criar, listar, editar e excluir Boards
- Criar, listar, editar e excluir Colunas dentro dos Boards
- Criar, listar, editar e excluir Cards (tarefas) dentro das Colunas
- Navegação entre boards, colunas e cards
- Validação básica no frontend e backend
- Tratamento de erros e mensagens para o usuário

---

## Estrutura dos Endpoints (REST API)

### Boards

| Método | Endpoint          | Descrição             |
|--------|-------------------|-----------------------|
| GET    | `/boards`         | Lista todos os boards  |
| POST   | `/boards`         | Cria um novo board    |
| PUT    | `/boards/{id}`    | Atualiza board existente |
| DELETE | `/boards/{id}`    | Exclui board          |

### Columns

| Método | Endpoint                     | Descrição                 |
|--------|------------------------------|---------------------------|
| GET    | `/boards/{boardId}/columns`  | Lista colunas do board    |
| POST   | `/boards/{boardId}/columns`  | Cria coluna no board      |
| PUT    | `/columns/{id}`              | Atualiza coluna           |
| DELETE | `/columns/{id}`              | Exclui coluna             |

### Cards

| Método | Endpoint              | Descrição                 |
|--------|-----------------------|---------------------------|
| GET    | `/columns/{columnId}/cards` | Lista cards da coluna   |
| POST   | `/cards`              | Cria card                 |
| PUT    | `/cards/{id}`         | Atualiza card             |
| DELETE | `/cards/{id}`         | Exclui card               |

---

## Como Rodar Localmente

### Pré-requisitos

- Java 17+ instalado
- Maven ou Gradle
- MySQL rodando localmente (ou via Docker)
- Configurar banco e usuário (exemplo no application.properties)

### Passos

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/seu-projeto.git
   cd seu-projeto

2. Configuração do Banco de Dados

Para começar, você precisa configurar o arquivo **`application.properties`** para conectar sua aplicação Spring Boot ao MySQL.

Encontre o arquivo em `src/main/resources/application.properties` e adicione (ou verifique se já estão lá) as seguintes linhas:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/board?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=board
spring.datasource.password=board
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**O que cada linha faz:**

* **`spring.datasource.url`**: Onde sua aplicação vai encontrar o MySQL. Certifique-se de que o **nome do banco de dados** (`board`) e a **porta** (`3306`) estejam corretos. Os parâmetros extras são pra evitar problemas de conexão e fuso horário.
* **`spring.datasource.username`**: O usuário para acessar o banco (aqui, `board`).
* **`spring.datasource.password`**: A senha do usuário (aqui, `board`).
* **`spring.jpa.hibernate.ddl-auto=update`**: Isso faz o Hibernate (que o Spring usa para interagir com o banco) atualizar automaticamente as tabelas baseadas no seu código. É ótimo para desenvolvimento, mas em produção, talvez você queira gerenciar as mudanças no banco manualmente.
* **`spring.jpa.show-sql=true`**: Mostra todas as consultas SQL que o Hibernate executa no console, o que ajuda muito na hora de depurar.

-----

## Rodando o Backend

Com o banco configurado, é hora de ligar sua aplicação Spring Boot.

Abra o terminal ou prompt de comando, vá até a pasta principal do seu projeto Spring Boot (onde está o `pom.xml`) e execute:

```bash
./mvnw spring-boot:run
```

Esse comando usa o **Maven Wrapper** para baixar o que for preciso e iniciar a aplicação. Você verá um monte de mensagens no console, indicando que a aplicação está subindo.

-----

## Acessando o Frontend

O frontend é um arquivo estático (`index.html`). Para ver ele funcionando:

* **Abra direto no navegador**: É só encontrar o arquivo `index.html` e abrir ele com seu navegador preferido.
* **Use um servidor estático (opcional)**: Se você precisa de mais controle ou se o frontend usa requisições CORS complexas, pode servir o `index.html` com um servidor web como Nginx, Apache ou até mesmo o próprio Spring Boot.

**Detalhes importantes para o Frontend:**

* **Ajuste as URLs da API (`API_BASE`) no JavaScript**: É super importante que você edite os arquivos JavaScript do seu frontend para que a variável `API_BASE` (ou o que for similar) aponte para onde seu backend Spring Boot está rodando. Se o backend estiver local na porta 8080, provavelmente será algo como `http://localhost:8080`.
* **Teste a API**: Para ter certeza de que seu backend está funcionando direitinho, recomendo usar ferramentas como **Postman** ou **Insomnia**. Com elas, você consegue enviar requisições (GET, POST, PUT, DELETE) para os endpoints da sua API e ver as respostas, o que é ótimo para testar e corrigir problemas.

-----

## Contato

Desenvolvido por **@s4mnara**