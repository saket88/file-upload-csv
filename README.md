# Read Me First
1. This application uses Spring Boot 2 as a web framework with Sprinng Boot Thymeleaf for web page templates

2. Spring Data JPA is used for persistence and H2 is in memory relational Database

3. The test data can be found in src/main/resources/user-location-data.csv


Steps to run(Please make sure Docker is running)


1. ./mvnw compile jib:dockerBuild

2. docker images.
         Just ensure that file-upload is there in your docker daemon
         
3. docker run --publish=8080:8080 file-upload:0.0.1-SNAPSHOT

