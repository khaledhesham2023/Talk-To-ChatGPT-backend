# Start with a base image containing Java runtime
FROM openjdk:11-jdk-slim as build

# Information about who maintains the Dockerfile
MAINTAINER khaled <khaledhesham2023@gmail.com>

# Add the application's jar to the container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
