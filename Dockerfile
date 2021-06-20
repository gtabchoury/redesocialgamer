FROM adoptopenjdk:11-jre-hotspot
ADD target/rede-social-gamer-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]