FROM openjdk:17-slim AS builder

# Install dependencies
RUN apt-get update && apt-get install -y \
    maven \
    openssl \
 && rm -rf /var/lib/apt/lists/*

WORKDIR /java
COPY . /java

# Build the JAR
RUN mvn package -Dmaven.test.skip=true

EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/java/target/akira-0.0.1-SNAPSHOT.jar"]