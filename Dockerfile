# Use the official OpenJDK 22 image as the base image
FROM openjdk:22-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper and project files to the container
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .
COPY src/ src/

# Grant execution permission to the Maven wrapper
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/todotask-0.0.1-SNAPSHOT.jar"]