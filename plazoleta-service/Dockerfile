FROM openjdk:17
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
ENV RDS_USERNAME=admin
ENV RDS_PASSWORD=pragma2023
COPY /build/libs/*.jar app.jar
EXPOSE 8099
CMD ["java", "-jar", "app.jar"]