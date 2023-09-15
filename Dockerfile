FROM openjdk:17-oracle
EXPOSE 7700
COPY target/springBootAuthorize-0.0.1-SNAPSHOT.jar authorize.jar
CMD ["java", "-jar", "authorize.jar"]
