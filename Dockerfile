# ====== STAGE 1 – BUILD ======
FROM gradle:8.7-jdk21-alpine AS build
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test --no-daemon

# ====== STAGE 2 – RUNTIME ======
FROM amazoncorretto:21-alpine
RUN adduser -D appuser
USER appuser
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
