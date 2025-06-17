# 📦 Using JPA to Create and Access a Database

## 🔗 Adding Dependencies

In your `build.gradle` file, add the dependencies for **Spring Data JPA** and **H2 Database** (an in-memory database used mainly for testing):

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa' // JPA dependency
    runtimeOnly 'com.h2database:h2' // H2 database (for testing)
}
```

## 🚗 Creating the Entity Car

Create the Car class inside the domain package. This class represents a table in the database.

```java
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This annotation maps the class to a database table
public class Car {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-incremented ID
    private Long id;

    private String brand;
    private String model;
    private String color;
    private String registrationNumber;
    private int modelYear;
    private int price;

    // Default constructor required by JPA
    public Car() {}

    // Constructor with parameters
    public Car(String brand, String model, String color, String registrationNumber, int modelYear, int price) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registrationNumber = registrationNumber;
        this.modelYear = modelYear;
        this.price = price;
    }

    // Getters and Setters (generate in your IDE)
}

```

## ⚙️ Configuring the H2 Database

Add the following properties in your application.properties file to configure the in-memory H2 database:

```java
# H2 Database URL
spring.datasource.url=jdbc:h2:mem:testdb

# Show SQL statements in the console
spring.jpa.show-sql=true

# Enable H2 Web Console
spring.h2.console.enabled=true

# Path to access the console
spring.h2.console.path=/h2-console
```

## 🖥️ Enabling the H2 Web Console

Enable the H2 web console to view and execute SQL commands:

```java
# Enable H2 Web Console
spring.h2.console.enabled=true

# Path to access the console
spring.h2.console.path=/h2-console
```

Access it in your browser:

http://localhost:8080/h2-console

## 🔄 Creating a CRUD Repository

Create an interface that extends CrudRepository to provide basic CRUD operations without writing SQL.

Built-in methods provided by CrudRepository include:

save() – Create or update an entity

findById() – Retrieve an entity by its ID

findAll() – Retrieve all entities

deleteById() – Delete an entity by its ID

## 🗄️ Setting Up a MariaDB Database

### 🔧 Updating Dependencies

In build.gradle, remove the H2 dependency and add the MariaDB driver:

```groovy
dependencies {
    runtimeOnly 'org.mariadb.jdbc:mariadb-java-client' // MariaDB driver
}
```

## 🗂️ Updating application.properties

Replace the H2 settings with the MariaDB configuration:

```java
# Database connection details
spring.datasource.url=jdbc:mariadb://localhost:3306/cardb
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

# Schema management
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=create-drop
```

## 🏗️ Understanding spring.jpa.hibernate.ddl-auto

This setting controls how Spring Boot manages the database schema:

| Value         | Description                                                                                     |
| ------------- | ----------------------------------------------------------------------------------------------- |
| `none`        | No schema changes; the app expects the database to be pre-configured.                           |
| `validate`    | Validate that the schema matches the entities without making changes.                           |
| `update`      | Update the database schema without dropping data (recommended for development).                 |
| `create`      | Drops and recreates the database schema every time the app starts (⚠️ data loss).               |
| `create-drop` | Like `create`, but also drops the database schema when the app stops (⚠️ use for testing only). |

> ⚠️ Warning: Avoid using create or create-drop in production as it will delete your data.

🚀 Next Steps
You can extend this project by adding:

1. ✅ REST API with @RestController

2. ✅ Service layer with business logic

3. ✅ DTOs for data transfer

4. ✅ Exception handling

