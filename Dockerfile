FROM openjdk:11
EXPOSE 8080
ADD build/libs/vumah-apis-0.0.1.jar vumah.jar
ENTRYPOINT ["java", "-jar", "/vumah.jar"]