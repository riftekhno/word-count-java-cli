# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the application JAR file and dependencies
COPY ./target/word-count-cli-1.0.jar /usr/src/app/word-count-cli-1.0.jar

# Copy the txt files
COPY ./files /usr/src/app

# This line is added for volume mount to share files
VOLUME /usr/src/app

# Run the application
CMD ["java", "-jar", "word-count-cli-1.0.jar"]
