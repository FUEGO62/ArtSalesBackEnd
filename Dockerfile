FROM maven:3-eclipse-temurin AS build
COPY . .
RUN mvn clean package -DskipTests
FROM eclipse-temurin:17-alpine
COPY --from=build /target/ArtSales-1.0-SNAPSHOT.jar Artsales.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=render", "-jar", "Artsales.jar"]
EXPOSE 8080