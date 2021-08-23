FROM adoptopenjdk:11-jre-hotspot
RUN mkdir /opt/app
COPY target/coder-travel-1.0-SNAPSHOT.jar /opt/app/coder-travel.jar

CMD ["java", "-jar", "/opt/app/coder-travel.jar"]
