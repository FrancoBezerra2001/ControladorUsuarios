Aqui está um arquivo **README.md** completo, bem estruturado e pronto para ser incluído na raiz do seu projeto. Ele explica o propósito da plataforma, a arquitetura utilizada, as regras de negócio implementadas (como a validação via TDD) e as instruções de execução.

---

# Controlador de Filme - Plataforma de Streaming 🎬

O **controlador_de_filme** é uma API REST robusta desenvolvida em **Spring Boot 3.x** projetada para gerenciar o controle de acesso, contas de usuários e a segurança de uma plataforma de streaming de vídeo.

O projeto consolida práticas recomendadas de mercado, como persistência de dados relaciais com **JPA/Hibernate**, segurança com **Spring Security** (incluindo criptografia de senhas com BCrypt), validação rigorosa de dados de entrada e o uso de **TDD (Test-Driven Development)** para blindar as regras de negócio.

---

## 🚀 Funcionalidades Principais

* **Gerenciamento de Usuários:** Cadastro estruturado via DTOs, validando unicidade de e-mail e consistência de dados.
* **Controle de Acesso e Perfis (RBAC):** Suporte a diferentes perfis de usuário (`ROLE_USER`, `ROLE_ADMIN`) e categorias de planos (`BASICO`, `PREMIUM`).
* **Segurança Avançada:** Uso do algoritmo **BCrypt** para hashing e criptografia de senhas antes do armazenamento no banco de dados.
* **Regras de Negócio Validadas por TDD:** Bloqueio automático de cadastros de titulares menores de 12 anos.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3.2.5**
* Spring Data JPA (Persistência de dados)
* Spring Security (Autenticação e Autorização)
* Spring Validation (Validação de beans/regra em requisições)
* Spring Web (Construção de endpoints REST)


* **H2 Database** (Banco de dados em memória para desenvolvimento ágil)
* **Lombok** (Produtividade e redução de código boilerplate)
* **JUnit 5 & Mockito** (Testes unitários e TDD)

---

## 📂 Arquitetura e Hierarquia de Pastas

O projeto adota o padrão em camadas (Controller-Service-Repository) para garantir a separação de responsabilidades e facilitar a manutenção do código.

```text
controlador_de_filme/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── streaming/
    │   │           └── controlador_de_filme/
    │   │               ├── ControladorDeFilmeApplication.java
    │   │               ├── config/
    │   │               │   └── SecurityConfig.java
    │   │               ├── controller/
    │   │               │   └── UsuarioController.java
    │   │               ├── dto/
    │   │               │   ├── UsuarioRequestDTO.java
    │   │               │   └── UsuarioResponseDTO.java
    │   │               ├── model/
    │   │               │   ├── Plano.java
    │   │               │   ├── Role.java
    │   │               │   └── Usuario.java
    │   │               ├── repository/
    │   │               │   └── UsuarioRepository.java
    │   │               └── service/
    │   │                   └── UsuarioService.java
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/
            └── com/
                └── streaming/
                    └── controlador_de_filme/
                        └── service/
                            └── UsuarioServiceTest.java

```

---

## 🧪 Praticando TDD (Test-Driven Development)

As regras de negócio críticas do sistema são validadas por testes unitários automatizados antes da exposição nos endpoints.

Para rodar a suíte de testes e validar o comportamento do ecossistema, execute o comando abaixo no terminal da raiz do projeto:

```bash
mvn test

```

Os cenários cobertos garantem:

1. O sucesso no cadastro quando todos os parâmetros e restrições legais são atendidos.
2. O lançamento de exceção (`IllegalArgumentException`) caso a idade do usuário seja inferior a 12 anos.

---

## 🛣️ Endpoints da API

### 1. Cadastrar Usuário

Cria um novo perfil de usuário com senha criptografada.

* **URL:** `/api/usuarios/cadastrar`
* **Método:** `POST`
* **Permissão:** Pública (Configurada no `SecurityConfig`)
* **Corpo da Requisição (JSON):**

```json
{
  "nome": "Alex Silva",
  "email": "alex@email.com",
  "senha": "senhaSegura123",
  "dataNascimento": "2000-05-15",
  "plano": "PREMIUM"
}

```

* **Resposta de Sucesso (201 Created):**

```json
{
  "id": 1,
  "nome": "Alex Silva",
  "email": "alex@email.com",
  "dataNascimento": "2000-05-15",
  "plano": "PREMIUM",
  "role": "ROLE_USER"
}

```

---

## ⚙️ Como Executar o Projeto

1. Clone o repositório ou copie os arquivos estruturados para sua máquina.
2. Certifique-se de ter o **Java 17** e o **Maven** instalados.
3. Na raiz do projeto (onde fica o arquivo `pom.xml`), execute o comando para baixar as dependências e iniciar o servidor:

```bash
mvn spring-boot:run

```

4. A aplicação estará rodando em `http://localhost:8080`.

### Acesso ao Banco de Dados (Console H2)

Durante a execução em modo de desenvolvimento, você pode inspecionar as tabelas criadas automaticamente pelo Hibernate acessando:

* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:streamingdb`
* **User Name:** `sa`
* **Password:** *(deixe em branco)*

---

Desenhado com foco em escalabilidade, segurança e aderência estrita às regras de negócio para plataformas modernas de entretenimento.
