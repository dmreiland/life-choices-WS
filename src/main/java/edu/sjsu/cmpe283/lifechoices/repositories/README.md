**repository** package contains all classes related to the db connection
========================================================================

This project uses [Spring Data MongoDB project](http://projects.spring.io/spring-data-mongodb/)


UserGeoHistoryRepository
------------------------

Repository to manage UserGeoHistory entity. All **CRUD** related methods are provided by [MongoRepository](http://docs.spring.io/spring-data/data-mongodb/docs/current/reference/html/repositories.html)

Geo Special [Documentation](http://docs.spring.io/spring-data/data-mongo/docs/1.4.x/reference/htmlsingle/)

####Custom Methods

| Method name                                                                                               | Description |
|-----------------------------------------------------------------------------------------------------------|-------------|
| List<UserGeoHistory> findByTimestampBetweenAndUserName(long starttime, long endtime, String userName)     | Will find all Geo history locations between two time stamps for a give username           |
| List<UserGeoHistory> findByUserName(String userName)                                                      | Will find all Geo history locations for a give username            |
| List<UserGeoHistory> findByPositionNear(Point p, Distance d)                                              | Will find all Geo history locations hear a give [Point](http://docs.spring.io/spring-data/mongodb/docs/1.4.0.RELEASE/api/org/springframework/data/mongodb/core/geo/Point.html) within provided [Distance](http://docs.spring.io/spring-data/mongodb/docs/1.4.0.RELEASE/api/org/springframework/data/mongodb/core/geo/Distance.html)            |
| List<UserGeoHistory> findByPositionWithin(Circle c)                                                       | Will find all Geo history locations within a given [Circle](http://docs.spring.io/spring-data/mongodb/docs/1.4.0.RELEASE/api/org/springframework/data/mongodb/core/geo/Circle.html)            |
| List<UserGeoHistory> findByPositionWithin(Box b)                                                          | Will find all Geo history locations within a given [Box](http://docs.spring.io/spring-data/mongodb/docs/1.4.0.RELEASE/api/org/springframework/data/mongodb/core/geo/Box.html)            |


UserRepository
--------------

Repository to manage User entity. All **CRUD** related methods are provided by [MongoRepository](http://docs.spring.io/spring-data/data-mongodb/docs/current/reference/html/repositories.html)

####Custom Methods

*none*
