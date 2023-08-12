FROM openjdk:17
MAINTAINER root
RUN mkdir sistema
COPY image/*.jar sistema/weather.jar
EXPOSE 9200
ENTRYPOINT ["java", "-jar", "/sistema/weather.jar"]