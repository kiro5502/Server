FROM adoptopenjdk/openjdk11
EXPOSE 8081
CMD ["mvn", "clean", "package"]
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} server.jar
ENTRYPOINT ["java", "-jar", "/server.jar"]