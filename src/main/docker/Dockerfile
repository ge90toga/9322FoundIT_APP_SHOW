FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD 9322-seek-app-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]