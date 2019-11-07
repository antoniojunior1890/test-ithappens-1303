## SEU CÓDIGO DE IMPLEMENTAÇÃO AQUI NESTE DIRETÓRIO

Test Antonio itHappens
================
Api rest em java para estoque de produtos

Tecnologias:
-------------
    * Java 8
    * Spring Boot 2.2.1
    * PostgreSQL 12
    * H2 1.4
    * Lombok 1.18.10
    * Swagger2 2.9.2

Execução:
--------------
## Requisitos:
```
- maven
- docker
- docker-compose
```

## Subir aplicação:
```
- $ cd /implementacao/happens
- $ mvn clean package -DskipTests
- $ docker build -t antoniojunior/happens .
- $ docker-compose up -d 

```

## Utilização:

### Aplicação:
```
- URL: http://localhost:8080/swagger-ui.html

```
### PgAdmin:
```
- URL: http://localhost:80
- Username: happens@happens.com
- Password: happens
```
### Adicionar novo server no PgAdmin:
```
- Host name/address: docker-postgres
- Port: 5432
- Maintenance: postgres
- Username: postgres
- Password: changeme
```
