FROM openjdk:15-slim
LABEL maintainer="minseok.lee@sk.com"
EXPOSE 8080
COPY build/libs/order-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar","/app.jar"]