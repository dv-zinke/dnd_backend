FROM java:8

LABEL maintanier="dv.zinke@gmail.com"

VOLUME /tmp

EXPOSE 9010

ARG JAR_FILE=target/write-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} write.jar

ENTRYPOINT ["java","-jar", "/write.jar"]
