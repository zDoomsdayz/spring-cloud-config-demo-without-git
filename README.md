# Spring Cloud Config Demo Using Directory

This project demonstrates how to use Spring Cloud Config Server to manage configurations for a Spring Boot application, loading configurations from a directory instead of Git.

## Setup Instructions

### Step 1: Running the Config Server

1. **Configuration**:

   - Ensure your `application.properties` for the Config Server is correctly configured to load configurations from the user directory:
     ```properties
     spring.application.name=server
     server.port=8888
     spring.profiles.active=native
     spring.cloud.config.server.native.search-locations=${user.dir}/spring-config
     ```

2. **Start the Config Server**:

   - Run the Spring Boot application for the Config Server (assumed to be on port 8888 by default).

3. **Verify the Config Server**:
   - Visit `http://localhost:8888/test-service/default` to ensure that the Config Server is loading the configuration correctly from the user directory.

### Step 2: Client Setup

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

### Step 3: Verify the Client Application

1. **Access the Client Endpoint**:

   - Visit `http://localhost:8080/hello` to see the message configured from the Spring Config Server.

2. **Debugging**:
   - Use the following actuator endpoints to view configuration properties and environment details:
     - `http://localhost:8080/actuator/configprops`
     - `http://localhost:8080/actuator/env`

## Summary

- **Config Server**: Hosts configuration properties and serves them to client applications. Configuration files are loaded from the user directory specified by `${user.dir}/spring-config`.
- **Client Application**: Fetches configuration properties from the Config Server at startup.

### Useful Links

- [Spring Cloud Config Documentation](https://spring.io/projects/spring-cloud-config)
- [Spring Boot Actuator Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)
