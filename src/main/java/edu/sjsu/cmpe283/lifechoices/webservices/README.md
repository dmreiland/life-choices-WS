New geo location
----------------------

URI: **/v1/geo/**

Method: **POST**

Consume type: **application/json**

Request Payload:

```json
{
    "userName":"maksim",
    "timestamp":1394932551,
    "latitude" : 37.415365 ,
    "longitude" : -122.089803
 }
```

Response type: **application/json**

**Response** Payload Sample:

```json
{
    "id":"53253c320364423e12a41b74",
    "timestamp":1394939032,
    "position":[37.41536331176758,-122.08980560302734],
    "userName":"maksim"
}
```

Response Status: **201 Created**

User Location History
---------------------
URI: **/v1/geo/history/{username}?starttime=123&endtime=234** *(starttime & endtime are optional. Default value will be a time BETWEEN now and 1 hr ago)*

Method: **GET**

Consume type: N/A

Request Payload: N/A

Response type: **application/json**

Response Payload Sample:

Example URL: **/v1/geo/history/maksim?starttime=1394939000&endtime=1394939999**

```json
{
    "endtime":1394939000,
    "username":"maksim",
    "starttime":1394939999,
    "history":[
                {
                    "id":"5325267e0364bc3dfe32da27",
                    "timestamp":1394939032,
                    "position":[37.41536331176758,-122.08980560302734],
                    "userName":"maksim"
                }
              ]
}
```

Google Places Service
----------------------

| URI                                                                                                               |  Method    | RESPONSE       | Sample URI |
|-------------------------------------------------------------------------------------------------------------------|------------|----------------|------------|
|/v1/places/{latitude}/{longitude}?radiusinmeters={integer : default=500}&type={string : if empty will return places for all types } | GET        | List of places | /v1/places/-33.8665433/151.1956316?radiusinmeters=500&type=park |
|/v1/places/types                                                                                                   | GET        | List of ALL Place Types | |

###Google Places Types ( [Source](https://developers.google.com/places/documentation/supported_types) )

- accounting
- airport
- amusement_park
- aquarium
- art_gallery
- atm
- bakery
- bank
- bar
- beauty_salon
- bicycle_store
- book_store
- bowling_alley
- bus_station
- cafe
- campground
- car_dealer
- car_rental
- car_repair
- car_wash
- casino
- cemetery
- church
- city_hall
- clothing_store
- convenience_store
- courthouse
- dentist
- department_store
- doctor
- electrician
- electronics_store
- embassy
- establishment
- finance
- fire_station
- florist
- food
- funeral_home
- furniture_store
- gas_station
- general_contractor
- grocery_or_supermarket
- gym
- hair_care
- hardware_store
- health
- hindu_temple
- home_goods_store
- hospital
- insurance_agency
- jewelry_store
- laundry
- lawyer
- library
- liquor_store
- local_government_office
- locksmith
- lodging
- meal_delivery
- meal_takeaway
- mosque
- movie_rental
- movie_theater
- moving_company
- museum
- night_club
- painter
- park
- parking
- pet_store
- pharmacy
- physiotherapist
- place_of_worship
- plumber
- police
- post_office
- real_estate_agency
- restaurant
- roofing_contractor
- rv_park
- school
- shoe_store
- shopping_mall
- spa
- stadium
- storage
- store
- subway_station
- synagogue
- taxi_stand
- train_station
- travel_agency
- university
- veterinary_care
- zoo












