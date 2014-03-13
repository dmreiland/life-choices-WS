Entities
=========

These classes will be mapped to the database

User
----

Content of this entity

    id : Long
    name : String


UserGeoHistory
-------------


| Name      |  Type     | Description |
------------|-----------|--------------|
| id        | Long      | Primary key of a record |
| time      | long      | UNIX Timestamp of the recorded location in milliseconds |
| longitude | long      | Geo location - longitude |
| latitude  | long      | Geo location - latitude |
| user      | User      | User to whom this location belong |
