FROM openjdk:17
ENV APP_HOME=/usr/app
ENV RDS_USERNAME=admin
ENV RDS_PASSWORD=pragma2023
WORKDIR $APP_HOME
COPY build/libs/*.jar app.jar
EXPOSE 8091
CMD ["java", "-jar", "app.jar"]
