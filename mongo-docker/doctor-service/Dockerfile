# Stage 1: Build the Spring Boot application
FROM maven:3.9.4-eclipse-temurin-21-alpine AS build

# Set the working directory
WORKDIR /app

# Copy Maven project files and source code
COPY pom.xml ./
COPY src ./src

# Package the Spring Boot application (skip tests)
RUN mvn clean install -DskipTests

# Stage 2: Run the application
FROM amazoncorretto:23-alpine-jdk

# Set working directory
WORKDIR /app

# Copy the packaged jar from the build stage
COPY --from=build /app/target/doctor-0.0.1-SNAPSHOT.jar app.jar

# Metadata (optional but useful)
LABEL maintainer="samarth"

# Expose port (optional if used in Kubernetes or similar)
#EXPOSE 8080

# Entry point to run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
