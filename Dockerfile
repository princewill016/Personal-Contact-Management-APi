# 1. Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-alpine

# 2. Set environment variables (optional)
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS=""

# 3. Create a directory for the application
WORKDIR /app

# 4. Copy the JAR file from the host to the container
COPY target/contact-0.0.1-SNAPSHOT.jar app.jar

# 5. Expose the port the app runs on
EXPOSE 9091

# 6. Define the command to run the app
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]

