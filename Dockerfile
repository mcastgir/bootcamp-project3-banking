FROM openjdk:12-jdk
COPY "./target/banking-0.0.1-SNAPSHOT.jar" "banking.jar"
ENTRYPOINT ["java","-jar","banking.jar"]