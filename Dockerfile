FROM gradle:7.6-jdk17 as build
COPY . /home/src
WORKDIR /home/src
RUN gradle clean build

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /home/src/build/libs/bt-device-booking-0.0.1.jar /home/app/bt-device-booking-0.0.1.jar
EXPOSE 8080:8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "/home/app/bt-device-booking-0.0.1.jar"]