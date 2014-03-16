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
| position  | double[]  | Geo location - [ latitude, longitude ] |
| userName  | String    | User to whom this location belong |
