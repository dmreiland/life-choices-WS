### Instruction to start web service:

Architecture
=============
```
   
                                +-----------------------------------+                           
                                |          Life Choices             |      +-------------------+
                                |       (Spring Boot v1.0)          |      |                   |
                                |                                   |      |                   |
                                |   +---------------------------+   |      |                   |
   +------------------+         |   |    RESTful Web Services   |<--|-----++     iOS Client    |
   | Google Places API+–––––+   |   |    (Spring Web Services)  ++--|----->|                   |
   +------------------+     |   |   +---------------------------+   |      |                   |
                            |   |         +             ^           |      |                   |
                            |   |         v             +           |      +---------+---------+
   +------------------+     |   |   +---------------------------+   |                ^          
   |    Yelp API      +–––––––––+   |       Services            |   |                +          
   +------------------+     |   |---|    (Business Logic)       +–––––––––+    +–––––+–––––+    
                            |   |   +---------------------------+   |     |   /             \   
                            |   |         +             ^           |     |  +   Apple Push  +  
                            |   |         v             +           |     ++>|  Notification |  
   +––––––––––––––––––+     |   |   +---------------------------+   |        +    Service    +  
   | WUnderground API +–––––+   |   |       DB Repository       |   |         \             /   
   +––––––––––––––––––+         |   |  (Spring Data MongoDB)    |   |          +–––––––––––+    
                                |   +---------------------------+   |                           
                                |         +             ^           |                           
                                +-----------------------------------+                           
                                          v             +                                       
                                    +---------------------------+                               
                                    |                           |                               
                                    |        MONGODB            |                               
                                    | Hosted on www.mongohq.com |                               
                                    |                           |                               
                                    +---------------------------+                               
```


*Edit diagram [HERE](http://www.asciiflow.com/#Draw916687349784866310/284750133)*

Dev Environment Requirements
=============================

!!! **Before starting any code make sure that applications.properties file is configured** See [THIS](https://github.com/cmpe273-indexzero/life-choices-WS/blob/master/src/main/resources/README.md) instructions

This project will only compile with Java 7 (1.7) [Download Here](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).

[This](https://github.com/spring-projects/spring-boot/pull/497) is a REASON/Problem why it is not possible to execute this project with Java 1.6

#### RUN PROJECT: Using IDE (IntelliJ *PREFERABLE*)

Install [lombok plugin](http://plugins.jetbrains.com/plugin/6317)

Configure IDE to execute main method in LifeChoicesApp class

Open this project in IDE and then configure it to run main program. You can just right click on the [LifeChoiceApp.java](https://github.com/cmpe273-indexzero/life-choices-WS/blob/master/src/main/java/edu/sjsu/cmpe283/lifechoices/LifeChoicesApp.java) class and run the program. Again, make sure Java 1.7 is configured.

#### RUN PROJECT: Using Maven
To build and run a project artifact, you need to navigate to the web service root folder (`cd {$project location}/web-service/`) and perform following tasks using Maven:

`$ mvn clean package`

`$ java -jar target/lifeChoices-web-service-1.0-SNAPSHOT.jar`

or in one shot:

`$ mvn clean package && java -jar target/lifeChoices-web-service-1.0-SNAPSHOT.jar`


#### Test
Try [THIS](http://localhost:8080/v1/geo/)

### Documentation
- [Web services](https://github.com/cmpe273-indexzero/life-choices-WS/tree/master/src/main/java/edu/sjsu/cmpe283/lifechoices/webservices)
- [Entities](https://github.com/cmpe273-indexzero/life-choices-WS/tree/master/src/main/java/edu/sjsu/cmpe283/lifechoices/entities)
- [Repository](https://github.com/cmpe273-indexzero/life-choices-WS/tree/master/src/main/java/edu/sjsu/cmpe283/lifechoices/repositories)
- [Configs](https://github.com/cmpe273-indexzero/life-choices-WS/tree/master/src/main/java/edu/sjsu/cmpe283/lifechoices/config)


### Configure Server

- connect: `ssh -i ~/.ssh/{key_file} ubuntu@54.193.8.183`

- Install Java 7 ( [Source](http://stackoverflow.com/a/16263651/51230) ) and other packages:

    ```
    sudo apt-get update
    sudo apt-get install openjdk-7-jdk maven git supervisor
    ```

- A. Check out the code:
    ```
    git clone https://github.com/cmpe273-indexzero/life-choices-WS.git
    ```

- B. Update, if already checked out:
    ```
    git pull
    ```


- Configure diamon

    Create a file `sudo vi /etc/supervisor/conf.d/cmpe273-lifechoices.conf`

    ```
    [program:cmpe273-life-choices-WS]
    command=usr/bin/mvn -f /home/ubuntu/life-choices-WS/pom.xml spring-boot:run
    user=ubuntu
    autostart=true
    autorestart=true
    startsecs=10
    startretries=3
    stdout_logfile=/home/ubuntu/life-choices-WS/log-cmpe273-life-choices-WS-stdout.log
    stderr_logfile=/home/ubuntu/life-choices-WS/log-cmpe273-life-choices-WS-stderr.log
    ```

- To control the application you would need to execute **supervisorctl**, which will present
you with a prompt where you could `start`, `stop`, and see the `status` of the app you specified in the `cmpe273-life-choices-WS.conf` file.




    ```
    $ sudo supervisorctl stop cmpe273-life-choices-WS
    $ sudo supervisorctl start cmpe273-life-choices-WS
    $ sudo supervisorctl restart cmpe273-life-choices-WS

    ```
All available commands for **supervisorctl**:

    ```
    add    clear  fg        open  quit    remove  restart   start   stop  update
    avail  exit   maintail  pid   reload  reread  shutdown  status  tail  version
    ```



- Restart the server `sudo reboot`

- Try to access services

    - http://54.193.8.183:8080/v1/places/?latitude=-33.8665433&longitude=151.1956316&radiusinmeters=500
    - http://54.193.8.183:8080/v1/food/?latitude=-33.8665433&longitude=151.1956316&deals=true
    - http://54.193.8.183:8080/v1/geo/
    - http://54.193.8.183:8080/v1/updates/


Spring Boot - Actuator
----------------------

| URL                                   | Description       |
|---------------------------------------|-------------------|
| http://54.193.8.183:8080/health       | Returns basic HTTP status of a server                  |
| http://54.193.8.183:8080/trace        | View details of every call                  |
| http://54.193.8.183:8080/metrics      | View stats of calls to the server                 |
| http://54.193.8.183:8080/dump         | View detailed server info                  |



