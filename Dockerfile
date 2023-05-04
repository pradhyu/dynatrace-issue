FROM openjdk:17-oracle
COPY ./target/*.jar /app.jar

ENTRYPOINT [ "java", "-Xmx1024m", "-Xms1024m", "-jar", "/app.jar" ]
