FROM maven:3.8.2-jdk-11 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn clean package

FROM openjdk:11-oraclelinux7
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/*.jar /app/
ENTRYPOINT ["java", "-jar", "metertomaggi-1.0.0-SNAPSHOT.jar"]