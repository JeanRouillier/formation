FROM openjdk:8-jre-alpine

#Environment variables
ENV INSTALL_HOME=/opt/formation

EXPOSE 8080 8081

ADD target/formation-*.jar $INSTALL_HOME/formation.jar

WORKDIR $INSTALL_HOME
CMD java -jar formation.jar -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap
