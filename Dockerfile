# Run build world-db script
FROM php:7-fpm
ADD build_db.sh /
ENTRYPOINT ["/bin/sh", "/build_db.sh"]

# Run the spring-boot-docker.jar image
FROM openjdk:8-jre-alpine
EXPOSE 8080
ADD target/spring-boot-docker.jar spring-boot-docker.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-docker.jar"]