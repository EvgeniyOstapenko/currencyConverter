FROM openjdk:15
ADD target/currency_converter.jar currency_converter.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "currency_converter.jar"]