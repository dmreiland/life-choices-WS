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

| URI                                                                                                                                              |  Method    | RESPONSE                  | Sample URI |
|--------------------------------------------------------------------------------------------------------------------------------------------------|------------|---------------------------|------------|
|/v1/places?latitude={double}&longitude={double}&radiusinmeters={integer : default=500}&type={string : if empty will return places for all types } | GET        | List of places            | /v1/places?latitude=-33.8665433&longitude=151.1956316&radiusinmeters=500&type=park |
|/v1/places/types                                                                                                                                  | GET        | List of ALL Place Types   |            |

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









Yelp Service
----------------------
| URI                                                                                       |  Method    | RESPONSE       | Sample URI                                           |
|-------------------------------------------------------------------------------------------|------------|----------------|------------------------------------------------------|
|/v1/food?latitude={double}&longitude={double}&keyword={string (defaults to 'restaurants'}  | GET        | List of places | /v1/food?latitude=-33.8665433&longitude=151.1956316  |


Response type: **application/json**

**Sample Response**:
```json
{
   "region":{
      "span":{
         "latitude_delta":0.15163511000000085,
         "longitude_delta":0.19816829999996344
      },
      "center":{
         "latitude":-33.880931250000003,
         "longitude":151.00376349999999
      }
   },
   "total":1975,
   "businesses":[
      {
         "is_claimed":false,
         "distance":9526.7824594653412,
         "mobile_url":"http://m.yelp.com.au/biz/el-manara-restaurant-lakemba",
         "rating_img_url":"http://s3-media1.ak.yelpcdn.com/assets/2/www/img/f1def11e4e79/ico/stars/v1/stars_5.png",
         "review_count":2,
         "name":"El-Manara Restaurant",
         "snippet_image_url":"http://s3-media1.ak.yelpcdn.com/photo/cVe0G83yACiYaCeGX0T6lg/ms.jpg",
         "rating":5.0,
         "url":"http://www.yelp.com.au/biz/el-manara-restaurant-lakemba",
         "location":{
            "city":"Lakemba",
            "display_address":[
               "143 Haldon St",
               "Lakemba New South Wales 2195",
               "Australia"
            ],
            "postal_code":"2195",
            "country_code":"AU",
            "address":[
               "143 Haldon St"
            ],
            "state_code":"NSW"
         },
         "phone":"+61297406762",
         "snippet_text":"It was 9pm on a Thursday night. I'd finished work and was starving. Most restaurants are closed around this time, so it's hard to get some good take out if...",
         "image_url":"http://s3-media2.ak.yelpcdn.com/bphoto/9ELItcLXDF6FlR0E5EbaDQ/ms.jpg",
         "categories":[
            [
               "Restaurants",
               "restaurants"
            ]
         ],
         "display_phone":"+61 2 9740 6762",
         "rating_img_url_large":"http://s3-media3.ak.yelpcdn.com/assets/2/www/img/22affc4e6c38/ico/stars/v1/stars_large_5.png",
         "id":"el-manara-restaurant-lakemba",
         "is_closed":false,
         "rating_img_url_small":"http://s3-media1.ak.yelpcdn.com/assets/2/www/img/c7623205d5cd/ico/stars/v1/stars_small_5.png"
      }
   
   ]
}

```

