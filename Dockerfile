FROM openjdk:latest
COPY ./target/SEMethodsSet088103G1CW.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "SEMethodsSet088103G1CW.jar", "db:33060"]