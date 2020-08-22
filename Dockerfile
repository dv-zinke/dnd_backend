FROM openjdk:8-jdk-alpine
LABEL maintanier="dv.zinke@gmail.com"
VOLUME /tmp
EXPOSE 9010
ARG JAR_FILE=build/libs/*.jar
ADD ${JAR_FILE} write.jar
ENTRYPOINT ["java","-jar", "/write.jar"]
