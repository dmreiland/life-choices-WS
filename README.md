### Instruction to start web service:

Java
====
This project will only compile with Java 7 (1.7) [Download Here](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
[This](https://github.com/spring-projects/spring-boot/pull/497) is a REASON/Problem why it is not possible to execute this project with Java 1.6

#### Maven
To build and run a project artifact, you need to navigate to the web service root folder (`cd {$project location}/web-service/`) and perform following tasks using Maven:

`$ mvn clean package`

`$ java -jar target/lifeChoices-web-service-1.0-SNAPSHOT.jar`

or in one shot:

`$ mvn clean package && java -jar target/lifeChoices-web-service-1.0-SNAPSHOT.jar`

#### Using IDE (IntelliJ or Eclipse)
Configure IDE to execute main method in LifeChoicesApp class


#### Test
For testing navigation to this user [http://localhost:8080/v1/geo/](http://localhost:8080/v1/geo/)

### Documentation
- [Web services](https://github.com/cmpe273-indexzero/life-choices-WS/tree/master/src/main/java/edu/sjsu/cmpe283/lifechoices/webservices)
- [Entities](https://github.com/cmpe273-indexzero/life-choices-WS/tree/master/src/main/java/edu/sjsu/cmpe283/lifechoices/entities)
- [Configs](https://github.com/cmpe273-indexzero/life-choices-WS/tree/master/src/main/java/edu/sjsu/cmpe283/lifechoices/config)




