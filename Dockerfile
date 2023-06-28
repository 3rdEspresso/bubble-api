# Build Stage
FROM gradle:jdk17 as build
# Alpine or Focal ask gpt
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY src src

RUN ./gradlew bootJar
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

# Run Stage
FROM amazoncorretto:17-alpine
VOLUME /tmp
ARG DEPENDENCY=/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.bubble.bubbleapi.BubbleApiApplication"]