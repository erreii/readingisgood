FROM openjdk:11
ADD target/readingisgood.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]