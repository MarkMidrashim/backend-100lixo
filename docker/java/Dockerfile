FROM maven:3.6.1-jdk-11-slim
VOLUME /tmp

ENV MEMORY_OPTS "-Xms512M -Xmx1024M -XX:MaxMetaspaceSize=256M"
ENV NON_BLOCKING_RANDOM "-Djava.security.egd=file:/dev/./urandom"
ENV JAVA_OPTS "$MEMORY_OPTS $NON_BLOCKING_RANDOM"

EXPOSE 80
ENTRYPOINT ["sh", "-C", "java $JAVA_OPTS -jar /coletaseletiva/target/coletaseletiva-0.0.1-SNAPSHOT.jar"]
