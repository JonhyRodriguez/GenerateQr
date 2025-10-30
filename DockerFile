# Use JDK 21 image to build the project
FROM eclipse-temurin:21-jdk

# Set working directory inside the container
WORKDIR /app

# Copy all project files into the container
COPY . .

# Build the project using Maven, skipping tests
RUN ./mvnw clean package -DskipTests

# Use a lighter JRE 21 image for running the app
FROM eclipse-temurin:21-jre

# Set working directory for runtime
WORKDIR /app

# Copy the compiled JAR from the build stage
COPY --from=0 /app/target/*.jar app.jar

# Expose port 8080 for incoming traffic
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
