FROM eclipse-temurin:17-jdk-alpine
COPY src /home/app/src
COPY pom.xml /home/app
EXPOSE 8081
ENTRYPOINT ["java","-jar","/home/app/target/learn-web-api-2024-0.0.1-SNAPSHOT.jar"]