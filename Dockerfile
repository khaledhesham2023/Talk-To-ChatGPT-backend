# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim
# Set the working directory in the container
#WORKDIR /app
ADD target/talk-to-chatgpt.jar talk-to-chatgpt.jar
# Add a volume pointing to tmp
#VOLUME /tmp
## App Jar File
#ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar
## Adding app jar to the container
#COPY ${JAR_FILE} app.jar
## Expose the port the application runs on
#EXPOSE 8080
# Run the jar file
ENTRYPOINT ["java", "-jar", "/talk-to-chatgpt.jar"]