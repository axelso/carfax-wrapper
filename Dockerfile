FROM openjdk:17-jdk-alpine
COPY carfax-wrapper-1.0-SNAPSHOT.jar carfax-wrapper-1.0-SNAPSHOT.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","/carfax-wrapper-1.0-SNAPSHOT.jar"]
