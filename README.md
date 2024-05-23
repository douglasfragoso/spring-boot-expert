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
4. Utilize as rotas fornecidas para realizar requisições ao sistema.
5. Verifique a documentação no Swagger acessando: [Swagger UI](http://localhost:8080/swagger-ui/index.html)


## Perfil de homologação
 - dev
 - MySQL

### Como Utilizar

1. Clone o projeto para sua IDE preferida.
2. Abra o MySQL Workbench
  - certifique-se das propriedades no [application-dev.properties](src/main/resources/application-dev.properties)
4. Execute o projeto Spring Boot com spring.profiles.active=${APP_PROFILE:dev} no aplication.properties.
5. Importe a collection para o Postman através do arquivo da [Collection](<Spring Boot Expert.postman_collection.json>).
6. Utilize as rotas fornecidas para realizar requisições ao sistema.
7. Verifique a documentação no Swagger acessando: [Swagger UI](http://localhost:8080/swagger-ui/index.html)
