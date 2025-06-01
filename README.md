# Lotostatic

Lotostatic é um projeto Spring Batch desenvolvido para automatizar o download da planilha da Mega-Sena disponibilizada pela Caixa Econômica Federal, processar os dados e armazená-los em um banco de dados MySQL.

## 📦 Funcionalidades

- Download automático da planilha `.xlsx` da Mega-Sena.
- Leitura e conversão dos dados usando EasyExcel.
- Mapeamento dos dados para uma entidade JPA.
- Persistência em banco de dados, evitando duplicações.
- Processamento de dados com Spring Batch.

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot 3.4.4
- Spring Batch
- EasyExcel (v4.0.3)
- MySQL
- Lombok

## 📁 Estrutura do Projeto

```
lotostatic/
├── config/            # Configurações do Spring Batch
├── model/             # Entidades e DTOs
├── processor/         # Processador de dados para evitar duplicações
├── reader/            # Leitor de Excel
├── mapper/            # Mapper de DTO para entidade
├── repository/        # Repositório JPA
├── util/              # Utilitários (ex: downloader e conversores)
└── LotostaticApplication.java
```

## ⚙️ Como executar

1. Configure o banco MySQL no `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/lotostatic
    username: root
    password: sua_senha
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

2. Execute a aplicação:

```bash
./mvnw spring-boot:run
```

O job será executado automaticamente no `CommandLineRunner`, baixando a planilha e persistindo os dados.

## ✅ Testes

A aplicação conta com testes unitários para:

- Conversão de datas (`LocalDateConverter`)
- Download de planilhas (`ExcelDownloader`)
- Processamento de dados (`SorterItemProcessor`)
- Mapeamento de DTO para entidade (`SorterMapper`)

Execute com:

```bash
./mvnw test
```

## 📌 Observações

- O campo `concourse` é utilizado para evitar duplicações no banco.
- O processamento é incremental, considerando apenas registros ainda não inseridos.