FROM openjdk:8-jdk-alpine
RUN apk add --no-cache bash && \
    apk add --no-cache curl
ARG JAR_FILE=some.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]