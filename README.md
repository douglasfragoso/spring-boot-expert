# Curso Spring Boot Expert

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/douglasfragoso/intensivo-java-spring/blob/main/LICENSE) 

# Sobre o projeto
O projeto consiste numa API RESTFul com Java e Spring boot para fazer requisições HTTP de um sistema de compras. 

# Tecnologias utilizadas

## Linguagem de Programação:

- **Java 21**: Linguagem de programação principal usada para desenvolver a aplicação.

## Ferramenta de Build:

- **Maven**: Ferramenta de automação de build usada para gerenciar dependências, compilar o código, empacotar a aplicação e executar testes.

## Framework:

- **Spring Boot**: Framework popular para construção de microsserviços e aplicações web. Proporciona uma experiência de desenvolvimento rápida ao simplificar a configuração e oferecer muitos recursos pré-construídos.

## Persistência:

- **Spring Data JPA**: Biblioteca do Spring que simplifica a interação com bancos de dados relacionais usando JPA (Java Persistence API). Fornece abstração e simplifica operações de acesso a dados.

## Segurança:

- **Spring Security**: Biblioteca do Spring que fornece um framework abrangente de segurança para autenticação, autorização e proteção de aplicações web.
- **JWT (JSON Web Token)**: Mecanismo de autenticação baseado em tokens usado no Spring Security. Tokens JWT são auto-contidos e podem ser usados para verificar a identidade de um usuário sem necessidade de consulta ao servidor.

## Bancos de Dados:

- **H2 Database**: Banco de dados em memória frequentemente usado para desenvolvimento e testes. É conveniente pois não requer instalação separada e funciona inteiramente em memória.
- **MySQL**: Sistema de gerenciamento de banco de dados relacional (RDBMS) popular e open-source usado para implantações em produção.

## Teste do Lado do Cliente:

- **Postman**: Ferramenta popular para fazer requisições HTTP e testar APIs. Pode ser usada para enviar requisições à aplicação desenvolvida e verificar respostas.

## Documentação de API:

- **Swagger**: Framework open-source para criação de documentação de API interativa. Pode gerar automaticamente documentação baseada em anotações de código e permite que os desenvolvedores explorem e compreendam facilmente a API de uma aplicação.

## Ferramentas de Simplificação de Código:

- **Bean Validation**: Padrão JSR (Java Specification Request) para validação de JavaBeans. Fornece anotações que podem ser usadas para definir regras de validação para campos em suas classes.
- **Lombok**: Biblioteca que pode gerar automaticamente código boilerplate, como getters, setters e métodos equals, reduzindo a quantidade de código que você precisa escrever.

# Modelos de domínios 

![Modelo de domínio](<UML - Spring boot Expert_page-0001.jpg>)

# Perfis de projeto

## Perfil de desenvolvimento e testes
 - test
 - H2 Database

### Como Utilizar

1. Clone o projeto para sua IDE preferida.
2. Execute o projeto Spring Boot com spring.profiles.active=${APP_PROFILE:test} no aplication.properties.
3. Importe a collection para o Postman através do arquivo [Collection](<Spring Boot Expert.postman_collection.json>).
4. Utilize as rotas fornecidas para realizar requisições ao sistema, lembrando de passar o token gerado na autenticação.
5. Verifique a documentação no Swagger acessando: [Swagger UI](http://localhost:8080/swagger-ui/index.html)


## Perfil de homologação
 - dev
 - MySQL

### Como Utilizar

1. Clone o projeto para sua IDE preferida.
2. Abra o MySQL Workbench
  - certifique-se das propriedades no [application-dev.properties](src/main/resources/application-dev.properties)
4. Execute o projeto Spring Boot com spring.profiles.active=${APP_PROFILE:dev} no aplication.properties.
5. Crie o seed disponível. 
6. Importe a collection para o Postman através do arquivo da [Collection](<Spring Boot Expert.postman_collection.json>).
7. Utilize as rotas fornecidas para realizar requisições ao sistema, lembrando de passar o token gerado na autenticação.
8. Verifique a documentação no Swagger acessando: [Swagger UI](http://localhost:8080/swagger-ui/index.html)

### Seed para Data Base

```sql
INSERT INTO tb_client (name, cpf, email, password, phone, profile) VALUES ('Douglas Fragoso', '12345678900', 'douglas@email.com', '$2a$12$Up0GoWwsrIAcdWGvuzTPuu/4OgMYIuzNA2EAEidm/DUPfksgFYGuG', '81523456789', 3);
INSERT INTO tb_client (name, cpf, email, password, phone, profile) VALUES ('Maria Silva', '12345678901', 'maria@email.com','$2a$12$Up0GoWwsrIAcdWGvuzTPuu/4OgMYIuzNA2EAEidm/DUPfksgFYGuG', '81123458789', 2);
INSERT INTO tb_client (name, cpf, email, password, phone, profile) VALUES ('João Pereira', '12345678902', 'joao@email.com', '$2a$12$Up0GoWwsrIAcdWGvuzTPuu/4OgMYIuzNA2EAEidm/DUPfksgFYGuG','81113456789', 1);
INSERT INTO tb_client (name, cpf, email, password, phone, profile) VALUES ('Ana Fragoso', '12345678903', 'ana@email.com', '$2a$12$Up0GoWwsrIAcdWGvuzTPuu/4OgMYIuzNA2EAEidm/DUPfksgFYGuG', '81823459789', 1);

INSERT INTO tb_product(name, description, price) VALUES ('Product 1', 'Description 1', 100.00);
INSERT INTO tb_product(name, description, price) VALUES ('Product 2', 'Description 2', 200.00);
INSERT INTO tb_product(name, description, price) VALUES ('Product 3', 'Description 3', 300.00);
INSERT INTO tb_product(name, description, price) VALUES ('Product 4', 'Description 4', 400.00);

INSERT INTO tb_order(client_id, date, total, status) VALUES (1, '2021-06-20T19:53:07Z', 500.00, 2);
INSERT INTO tb_order(client_id, date, total, status) VALUES (2, '2021-06-20T19:53:07Z', 400.00, 1);
INSERT INTO tb_order(client_id, date, total, status) VALUES (3, '2021-06-20T19:53:07Z', 300.00, 3);
INSERT INTO tb_order(client_id, date, total, status) VALUES (4, '2021-06-20T19:53:07Z', 800.00, 4);

INSERT INTO tb_order_item(order_id, product_id, quantity) VALUES (1, 1, 2);
INSERT INTO tb_order_item(order_id, product_id, quantity) VALUES (1, 3, 1);
INSERT INTO tb_order_item(order_id, product_id, quantity) VALUES (2, 2, 2);
INSERT INTO tb_order_item(order_id, product_id, quantity) VALUES (3, 3, 1);
INSERT INTO tb_order_item(order_id, product_id, quantity) VALUES (4, 4, 2);
```
### Obs: senha para login de qualquer cadastro do seed: 123456

## Criação de arquivo JAR ou WAR pelo perfis no `pom.xml`

### 1. Desenvolvimento - JAR

- **Caso tenha o Maven instalado e configurado corretamente:**
  - Abrir prompt de comando na pasta do arquivo:
    ```sh
    mvn clean package
    ```

- **Caso não tenha o Maven instalado:**
  - Abrir prompt de comando na pasta do arquivo:
    ```sh
    ./mvnw clean package
    ```

### 2. Produção - WAR

- **Caso tenha o Maven instalado e configurado corretamente:**
  - Abrir prompt de comando na pasta do arquivo:
    ```sh
    mvn clean package -P producao
    ```

- **Caso não tenha o Maven instalado:**
  - Abrir prompt de comando na pasta do arquivo:
    ```sh
    ./mvnw clean package -P producao
    ```
