FROM openjdk:8
ADD target/applicationJarFile.jar applicationJarFile.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "docker-spring-boot.jar"]