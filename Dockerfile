FROM java:11
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} server.jar
ENTRYPOINT ["java", "-jar", "/server.jar"]