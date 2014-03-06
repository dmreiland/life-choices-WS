### Instruction to start web service:

#### Maven
To build and run a project artifact, you need to navigate to the web service root folder (`cd {$project location}/web-service/`) and perform following tasks using Maven:

`$ mvn clean package`

`$ java -jar target/lifeChoices-web-service-1.0-SNAPSHOT.jar`

or in one shot:

`$ mvn clean package && java -jar target/lifeChoices-web-service-1.0-SNAPSHOT.jar`

#### Using IDE (IntelliJ or Eclipse)
Configure IDE to execute main method in LifeChoicesApp class


#### Test
Then navigate to This is [http://localhost:8080/v1/geo/](http://localhost:8080/v1/geo/)

Default login is user:password