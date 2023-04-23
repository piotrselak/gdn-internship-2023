FROM openjdk:17-jdk-alpine AS build

WORKDIR /build
COPY . .
RUN apk add maven
RUN mvn clean package

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /build/target/gdn-internship-2023-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java","-jar","gdn-internship-2023-0.0.1-SNAPSHOT.jar"]
