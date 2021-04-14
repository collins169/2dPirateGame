FROM openjdk:8-jdk-alpine
EXPOSE 8057
MAINTAINER cashlet.com
COPY target/*.jar 2dPirate.jar
ENTRYPOINT ["java","-jar","/2dPirate.jar"]