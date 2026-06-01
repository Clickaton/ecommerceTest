# eCommerce Platform

A full-stack Spring Boot eCommerce application providing essential online shopping functionalities such as user management, product browsing, cart operations, transaction checkout, and an admin dashboard.

## Description

This project serves as a comprehensive eCommerce backend and frontend integrated system. Users can register, log in, browse products, add them to their cart, and checkout with mock payment processing (MercadoPago) and email confirmations. Administrators have access to a dashboard to manage users and products.

## Technology Stack

* **Backend:** Java 17, Spring Boot 2.7.x, Spring MVC, Spring Data JPA, Spring Security, Spring Mail
* **Frontend:** Thymeleaf, Bootstrap 5, HTML/CSS
* **Database:** MySQL (Production), H2 (Testing)
* **Build Tool:** Maven

## Prerequisites

To run this application locally, you will need:
* Java Development Kit (JDK) 17 installed
* Maven installed
* MySQL server installed and running locally
* Git for version control

## Environment Variables / Configuration

Update the `src/main/resources/application.properties` file with your specific database credentials and email configurations:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce?allowPublicKeyRetrieval=true&useSSL=false&useTimezone=true&serverTimezone=GMT&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=root

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_email_password
```

## Installation and Execution

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```

2. **Database Setup:**
   Ensure your MySQL server is running and create a database named `ecommerce`.

3. **Build the application:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the application:**
   Open your browser and navigate to `http://localhost:8080/`.

## Testing

The project uses JUnit 5 and MockMvc for integration testing. The tests utilize an in-memory H2 database.
Run the tests using:
```bash
mvn clean test
```

## Architecture and Structure

The application follows a standard Layered Architecture:

* **Controllers (`com.ventas.eCommerce.controller`):** Handles incoming HTTP requests, binds parameters, delegates business logic to Services, and returns views (Thymeleaf templates) or redirects.
* **Services (`com.ventas.eCommerce.Services`):** Contains the core business logic (e.g., managing the cart, registering users, handling transactions and mocked payment/emailing).
* **Repositories (`com.ventas.eCommerce.repositories`):** Spring Data JPA interfaces for database operations.
* **Entities (`com.ventas.eCommerce.entities`):** JPA annotated models representing the database schema (User, Product, Cart, Transaction, Image).

The templates are located under `src/main/resources/templates` and static assets under `src/main/resources/static`.
