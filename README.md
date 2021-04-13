## springboot-crud-examples 
 Spring Boot CRUD examples is a simple crud example for a `board` entity using Spring Data JPA and Querydsl.
 
## The details
This project is based on Spring Boot
 - packages used
    - Spring Data JPA
    - Querydsl 
    - thymeleaf
    - mariaDB  
    - maven
    - lombok
    
<img src = "https://user-images.githubusercontent.com/40849381/114607069-0b837b80-9cd7-11eb-8257-245a6a6ce577.png" width="600px">
<img src = "https://user-images.githubusercontent.com/40849381/114607704-d0ce1300-9cd7-11eb-8823-5acbd8925e20.png" width="600px">
<img src = "https://user-images.githubusercontent.com/40849381/114607782-e5121000-9cd7-11eb-9e8e-590e0419b0a4.png" width="600px">

## Installation
The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies
 + If an error occurs for the Qclass
   See 
<a href="https://stackoverflow.com/questions/45794079/query-dsl-q-type-classes-not-generated" target="_blank">here</a>
   
## Database setting
Create a mariaDB database with the name `crud` and add the config to `/resources/application.properties`.

Add your db info to the default settings :
```
spring.datasource.driverClassName=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mariadb://localhost:3306/crud
spring.datasource.username=root
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.MariaDB103Dialect
```

## Usage
Run the project through the IDE and head out to http://localhost:8080
