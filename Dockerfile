FROM openjdk:latest
COPY ./target/SEMethodsSet088103G1CW-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "SEMethodsSet088103G1CW-1.0-SNAPSHOT-jar-with-dependencies.jar"]