From openjdk:8-jdk-alpine
copy ./target/demo-0.0.1-SNAPSHOT.jar womb.jar
CMD ["java","-jar","womb.jar"]
