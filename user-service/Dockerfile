FROM openjdk:17
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY build/libs/*.jar app.jar
COPY src/main/resources/bootstrap.yml $APP_HOME
EXPOSE 8091
CMD ["java", "-jar", "app.jar"]
