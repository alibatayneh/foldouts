FROM gradle:jdk17 AS build
COPY . .
RUN gradle clean build

FROM openjdk:17.0.1-slim
COPY --from=build /home/gradle/build/libs/foldouts-0.0.1-SNAPSHOT.jar foldouts.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","foldouts.jar"]