FROM openjdk:17
ENV APP_HOME=/usr/app
ENV RDS_USERNAME=admin
ENV RDS_PASSWORD=pragma2023
WORKDIR $APP_HOME
COPY build/libs/*.jar app.jar
COPY src/main/resources/bootstrap.yml $APP_HOME
EXPOSE 8091
CMD ["java", "-jar", "app.jar"]
