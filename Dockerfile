From openjdk:8
copy target/UserManagement-0.0.1-SNAPSHOT.jar UserManagement-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","UserManagement-0.0.1-SNAPSHOT.jar"]
EXPOSE 8081
