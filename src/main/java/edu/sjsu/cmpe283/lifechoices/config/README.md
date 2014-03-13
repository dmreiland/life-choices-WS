Security Configuration file
=============================

Basic authentication method to use plain user name and password

User name   | Password  | Roles         |
------------|-----------|---------------|
useradmin   |zzzzzz     | USER, ADMIN   |
admin       |zzzzzz     | ADMIN         |
user1       |zzzzzz     | USER          |
user2       |zzzzzz     | USER          |
user3       |zzzzzz     | USER          |
user4       |zzzzzz     | USER          |


DB Config (Mongo)
==================

Mongo docs http://docs.spring.io/spring-data/data-mongo/docs/1.4.0.RC1/reference/html/mongo.repositories.html

user: lifechoicesuser
pass: lifechoicesuserpa$5
uri : mongodb://<user>:<password>@oceanic.mongohq.com:10032/lifechoices_mdb