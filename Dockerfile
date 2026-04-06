# syntax=docker/dockerfile:1
FROM maven:3.9.6-eclipse-temurin-21 as build
WORKDIR /app
COPY . .
RUN --mount=type=cache,target=/root/.m2 mvn clean install -Dmaven.test.skip

FROM build as prod
WORKDIR /app
ENV SPRING_PROFILES_ACTIVE=prod
COPY --from=build /app/spring-app/target/body-builder.jar .
CMD ["java", "-jar", "body-builder.jar"]


FROM prod as dev
ENV SPRING_PROFILES_ACTIVE=dev
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000", "-jar", "body-builder.jar"]