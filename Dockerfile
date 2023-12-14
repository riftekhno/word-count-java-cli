# Use an official Maven image as a parent image
FROM maven:3.8.4-openjdk-11-slim AS builder

# Copy the Maven POM file
COPY pom.xml /usr/src/app

# Copy the src file
COPY src /usr/src/app/src

# Download dependencies and build the application
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the built JAR file from the Maven image
COPY --from=builder /usr/src/app/target/word-count-cli-1.0.jar /usr/src/app/word-count-cli-1.0.jar

# Copy the txt files
COPY ./files /usr/src/app

# This line is added for volume mount to share files
VOLUME /usr/src/app

# Run the application
CMD ["java", "-jar", "word-count-cli-1.0.jar"]
