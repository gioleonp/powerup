FROM openjdk:17
ENV APP_HOME = /usr/app
WORKDIR $APP_HOME
COPY build/libs/*.jar app.jar
ENV RDS_USERNAME = admin
ENV RDS_PASSWORD = pragma2023
EXPOSE 8091
CMD ["java", "-jar", "app.jar"]
