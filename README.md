# RestaurantBlue 🍽️

Sistema de gerenciamento de restaurante desenvolvido em **Java** como projeto educacional da disciplina **Tecnologias para Back-End**.

O objetivo do projeto é aplicar conceitos fundamentais de desenvolvimento back-end, incluindo modelagem de entidades, APIs REST, persistência de dados, arquitetura em camadas e boas práticas de desenvolvimento utilizando o ecossistema Java. :contentReference[oaicite:0]{index=0}

## 📚 Sobre o Projeto

O RestaurantBlue foi criado com fins acadêmicos para auxiliar no aprendizado de tecnologias utilizadas no desenvolvimento de aplicações back-end modernas.

A aplicação permite o gerenciamento de informações relacionadas a um restaurante, utilizando operações de cadastro, consulta, atualização e remoção (CRUD) de entidades do sistema.

## 🚀 Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven
- Lombok
- Banco de Dados Relacional
- REST API

## 🏗️ Arquitetura

O projeto segue uma estrutura organizada em camadas:

- **Controller** → Recebe as requisições da API.
- **Service** → Contém as regras de negócio.
- **Repository** → Responsável pelo acesso aos dados.
- **Model/Entity** → Representação das entidades do sistema.

Essa separação facilita a manutenção, organização e evolução do software.

## 📋 Funcionalidades

- Cadastro de clientes
- Cadastro de funcionários
- Gerenciamento de pedidos
- Atualização de informações
- Exclusão de registros
- Consulta de dados através de endpoints REST

## ⚙️ Como Executar

### Pré-requisitos

- Java 21 
- Maven
- IDE Java (IntelliJ IDEA, Eclipse ou VS Code)

### Clone o repositório

```bash
git clone https://github.com/jotamonteiro/RestaurantBlue.git
```

### Acesse a pasta

```bash
cd RestaurantBlue
```

### Execute o projeto

```bash
mvn spring-boot:run
```

Ou execute a classe principal da aplicação diretamente pela sua IDE.

## 📂 Estrutura do Projeto

```text
src/
 ├── controller
 ├── service
 ├── repository
 ├── model
 ├── dto
 └── config
```

## 🎓 Objetivo Acadêmico

Este projeto foi desenvolvido exclusivamente para fins de aprendizado na disciplina **Tecnologias para Back-End**, com foco na prática de:

- Programação Orientada a Objetos (POO)
- Desenvolvimento de APIs REST
- Persistência de dados com JPA
- Arquitetura em camadas
- Boas práticas de desenvolvimento em Java

## 👨‍💻 Autor

João Monteiro

GitHub: https://github.com/jotamonteiro

---

Projeto desenvolvido para fins educacionais.

Acesso ao Swagger: 
http://localhost:8080/swagger-ui/index.html#/

Acesso ao H2: 
http://localhost:8080/h2

Login H2:
JDBC URL: jdbc:h2:file:~/restaurantBlue
User Name: jota
