FROM openjdk:17.0
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY build/libs/*.jar app.jar
EXPOSE 8088
ENV GIT_USERNAME = giovannileonp
ENV GIT_PASSWORD = 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
CMD ["java", "-jar", "app.jar"]