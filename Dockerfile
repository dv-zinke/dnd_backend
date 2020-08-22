FROM java:8
LABEL maintanier="dv.zinke@gmail.com"
VOLUME /tmp
EXPOSE 9010
ARG JAR_FILE=build/libs/*.jar
ADD ${JAR_FILE} write.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/write.jar"]
