## springboot-crud-examples 
 Spring Boot CRUD examples is a simple crud example for a `board` entity using Spring Data JPA and Querydsl.
### The details
This project is based on Spring Boot
 - packages used
    - Spring Data JPA
    - Querydsl 
    - thymeleaf
    - mariaDB  
    - maven
    
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
