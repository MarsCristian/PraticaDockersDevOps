#imagem base do Maven
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

#imagem base do OpenJDK para rodar a aplicação
FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

#Garantir que o app so vai rodar depois do BD se n vai dar ruim
HEALTHCHECK --interval=30s --timeout=30s --start-period=5s --retries=3 \
  CMD curl --silent --fail http://db:3306/ || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
