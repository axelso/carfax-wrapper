FROM openjdk:17-jdk-alpine
COPY target/carfax-wrapper-1.0-SNAPSHOT.jar carfax-wrapper-1.0-SNAPSHOT.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","/carfax-wrapper-1.0-SNAPSHOT.jar", "--server.port=8086", "--server.address=0.0.0.0"]
