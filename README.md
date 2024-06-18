# Spring Cloud Config Demo

This project demonstrates how to use Spring Cloud Config Server to manage configurations for a Spring Boot application.

## Setup Instructions

### Step 1: Config Server Setup

1. **Copy Configuration Files**:

   - Copy the `spring-config` directory and paste it to `C:\spring-config`.

2. **Initialize Git Repository**:
   - Navigate to `C:\spring-config` and run the following commands to initialize a git repository and commit the configuration files:
     ```sh
     cd C:\spring-config
     git init
     git add .
     git commit -m "Initial commit of configuration files"
     ```

### Step 2: Running the Config Server

1. **Start the Config Server**:

   - Run the Spring Boot application for the Config Server (assumed to be on port 8888 by default).

2. **Verify the Config Server**:
   - Visit `http://localhost:8888/test-service/default` to ensure that the Config Server is loading the configuration correctly.

### Step 3: Client Setup

1. **Update `pom.xml`**:

   - Ensure your `pom.xml` includes the necessary dependencies for Spring Cloud Config and Actuator:

     ```xml
     <dependencies>
     	<dependency>
     		<groupId>org.springframework.boot</groupId>
     		<artifactId>spring-boot-starter-web</artifactId>
     	</dependency>

     	<dependency>
     		<groupId>org.springframework.boot</groupId>
     		<artifactId>spring-boot-starter-test</artifactId>
     		<scope>test</scope>
     	</dependency>
     	<dependency>
     		<groupId>org.springframework.boot</groupId>
     		<artifactId>spring-boot-starter-actuator</artifactId>
     	</dependency>
     	<dependency>
     		<groupId>org.springframework.cloud</groupId>
     		<artifactId>spring-cloud-starter-config</artifactId>
     		<version>4.1.2</version>
     	</dependency>
     </dependencies>
     ```

2. **Update `application.properties`**:

   - Ensure your `application.properties` file is correctly configured:
     ```properties
     spring.application.name=test-service
     spring.config.import=optional:configserver:http://localhost:8888
     logging.level.org.springframework.cloud=DEBUG
     logging.level.org.springframework.web.client.RestTemplate=DEBUG
     management.endpoints.web.exposure.include=*
     ```

3. **Run the Client Application**:
   - Start the Spring Boot client application (assumed to be on port 8080 by default).

### Step 4: Verify the Client Application

1. **Access the Client Endpoint**:

   - Visit `http://localhost:8080/hello` to see the message configured from the Spring Config Server.

2. **Debugging**:
   - Use the following actuator endpoints to view configuration properties and environment details:
     - `http://localhost:8080/actuator/configprops`
     - `http://localhost:8080/actuator/env`

## Summary

- **Config Server**: Hosts configuration properties and serves them to client applications.
- **Client Application**: Fetches configuration properties from the Config Server at startup.

### Useful Links

- [Spring Cloud Config Documentation](https://spring.io/projects/spring-cloud-config)
- [Spring Boot Actuator Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)
