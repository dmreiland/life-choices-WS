API for the all available Web Services


- [New geo location V1](#new-geo-location)
- [New geo location V2 with Check In/Out](#new-geo-loc-v2)
- [User Location History](#user-loc-his)
- [Google Places Service](#google-places-service)
- [Yelp Service](#yelp-service)
- [User Updates](#user-update)
- [APN (Apple Push Notification) Web Service](#apn-ws)
- [Find Friends In the Area](#find-friends-in-the-area)
- [Find all geo locations for user for a particular type](#find-all-geo-locations-for)



<a name="new-geo-location"></a> New geo location V1
-----------------------------------------------------

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

<a name="new-geo-loc-v2"></a>New geo location V2 with Check In/Out
----------------------

URI: **/v2/geo/**

Method: **POST**

Consume type: **application/json**

Request Payload (three different samples of how to send data):
Available types for geoHistoryType: `CHECKIN` - User checked in at this location, `CHECKOUT` - User checked out from this location, `null` - regular location data

```json
[
  {
	"userName":"MaksimTestingCHeckin",
    "latitude":123.1232312312312,
    "longitude":-12.12312312,
    "timestamp":123456789,
    "geoHistoryType":"CHECKIN",
    "yelpId":"HI-ROB-THIS-IS-A-TEST1"
  },
  {
	"userName":"MaksimTestingCHeckin",
    "latitude":123.1232312312312,
    "longitude":-12.12312312,
    "timestamp":123456790,
    "geoHistoryType":"CHECKOUT",
    "yelpId":"HI-ROB-THIS-IS-A-TEST2"
  },
  {
	"userName":"MaksimTestingCHeckin",
    "latitude":123.1232312312312,
    "longitude":-12.12312312,
    "timestamp":123456791,
    "geoHistoryType":null
  },
  {
   	"userName":"MaksimTestingCHeckin",
    "latitude":123.1232312312312,
    "longitude":-12.12312312,
    "timestamp":123456791
   }
]
```

Response type: **application/json**

**Response** Payload Sample:

```json
[
    {
        "id":"5358979903640516292c2f3c",
        "timestamp":123456789,
        "position":[123.12322998046875,-12.123123168945312],
        "userName":"MaksimTestingCHeckin",
        "historyType":"CHECKIN"
    },
    {
        "id":"5358979903640516292c2f3d",
        "timestamp":123456790,
        "position":[123.12322998046875,-12.123123168945312],
        "userName":"MaksimTestingCHeckin",
        "historyType":"CHECKOUT"
    },
    {
        "id":"5358979a03640516292c2f3e",
        "timestamp":123456791,
        "position":[123.12322998046875,-12.123123168945312],
        "userName":"MaksimTestingCHeckin",
        "historyType":null
    },
    {
        "id":"5358979a03640516292c2f3e",
        "timestamp":123456791,
        "position":[123.12322998046875,-12.123123168945312],
        "userName":"MaksimTestingCHeckin",
        "historyType":null
    }
]
```

Response Status: **201 Created**

<a name="user-loc-his"></a>User Location History
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

<a name="google-places-service"></a>Google Places Service
----------------------

| URI                                                                                                                                              |  Method    | RESPONSE                  | Sample URI |
|--------------------------------------------------------------------------------------------------------------------------------------------------|------------|---------------------------|------------|
|/v1/places?latitude={double}&longitude={double}&radiusinmeters={integer : default=500}&type={string : if empty will return places for all types } | GET        | List of places            | /v1/places?latitude=-33.8665433&longitude=151.1956316&radiusinmeters=500&type=park |
|/v1/places/types                                                                                                                                  | GET        | List of ALL Place Types   |            |

###Google Places Types ( [Source](https://developers.google.com/places/documentation/supported_types) )
accounting, airport, amusement_park, aquarium, art_gallery, atm, bakery, bank, bar, beauty_salon, bicycle_store, book_store, bowling_alley,
bus_station, cafe, campground, car_dealer, car_rental, car_repair, car_wash, casino, cemetery, church, city_hall, clothing_store, convenience_store,
courthouse, dentist, department_store, doctor, electrician, electronics_store, embassy, establishment, finance, fire_station, florist, food, funeral_home,
furniture_store, gas_station, general_contractor, grocery_or_supermarket, gym, hair_care, hardware_store, health, hindu_temple, home_goods_store,
hospital, insurance_agency, jewelry_store, laundry, lawyer, library, liquor_store, local_government_office, locksmith, lodging, meal_delivery, meal_takeaway,
mosque, movie_rental, movie_theater, moving_company, museum, night_club, painter, park, parking, pet_store, pharmacy, physiotherapist, place_of_worship,
plumber, police, post_office, real_estate_agency, restaurant, roofing_contractor, rv_park, school, shoe_store, shopping_mall, spa, stadium, storage, store,
subway_station, synagogue, taxi_stand, train_station, travel_agency, university, veterinary_care, zoo









<a name="yelp-service"></a>Yelp Service
----------------------
| URI                                                                                       |  Method    | RESPONSE       | Sample URI                                           |
|-------------------------------------------------------------------------------------------|------------|----------------|------------------------------------------------------|
|/v1/food?latitude={double}&longitude={double}&keyword={string (defaults to 'restaurants'} (optional parameters: 'radius' - max distance in meters, 'deals' - boolean to list only places that have deals  | GET        | List of places | /v1/food?latitude=-33.8665433&longitude=151.1956316  |
|/v1/events?latitude={double}&longitude={double}&keyword={string (defaults to 'events'} (optional parameters: 'radius' - max distance in meters, 'deals' - boolean to list only places that have deals  | GET        | List of places | /v1/events?latitude=-33.8665433&longitude=151.1956316  |


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



<a name="user-update"></a>User Updates
----------------------
| URI                                                                                       |  Method    | RESPONSE       | Sample URI                                           |
|-------------------------------------------------------------------------------------------|------------|----------------|------------------------------------------------------|
|/v1/updates?latitude={double}&longitude={double}&width={int}&height={height} Defaults to SJSU, 640x480              | GET        | List of Map/ETA/Distance/Weather for places | /v1/food?latitude=-33.8665433&longitude=151.1956316&width=320&240  |


Response type: **application/json**

**Sample Response**:

```json
{
  "latitude": 37.334679,
  "longitude": -121.881113,
  "weather": {
        "response": {
            "version": "0.1",
            "features": {
                "forecast": 1
            },
            "termsofService": "http://www.wunderground.com/weather/api/d/terms.html"
        },
        "forecast": {
            "txt_forecast": {
                "date": "8:00 PM PDT",
                "forecastday": [
                    {
                        "period": 0,
                        "icon": "clear",
                        "title": "Monday",
                        "icon_url": "http://icons-ak.wxug.com/i/c/k/clear.gif",
                        "fcttext": "Clear in the morning, then partly cloudy. High of 81F. Winds from the NW at 5 to 15 mph."
                    },
                    {
                        /* ... */
                    }
                ]
            },
            "simpleforecast": {
                "forecastday": [
                    {
                        "date": {
                            "epoch": "1397541600",
                            "day": 14,
                            "month": 4,
                            "year": 2014,
                            "hour": 23,
                            "min": "00",
                            "tz_short": "PDT"
                        },
                        "period": 1,
                        "high": {
                            "fahrenheit": "81",
                            "celsius": "27"
                        },
                        "low": {
                            "fahrenheit": "54",
                            "celsius": "12"
                        },
                        "conditions": "Clear",
                        "icon": "clear",
                        "skyicon": "mostlysunny",
                        "avehumidity": 62,
                        "maxhumidity": 80,
                        "minhumidity": 32,
                        "icon_url": "http://icons-ak.wxug.com/i/c/k/clear.gif",
                        "avewind": {
                            "mph": 7,
                            "kph": 11,
                            "dir": "NW",
                            "degrees": 315
                        }
                    },
                    {
                        /* ... */
                    }
                ]
            }
        }
    },
  "destinations": [
    {
      "latitude": 37.390052,
      "longitude": -121.9781685,
      "weather": null,
      "maps_url": "http:\/\/maps.google.com\/maps\/api\/staticmap?key={key}}&size=640x480&maptype=roadmap&sensor=false&markers=37.334679,-121.881113|37.390052,-121.9781685&path=weight:3|color:blue|enc:_{zbF|x{fVjGeEvCmBtLiIfEhLfBrEVW\\SPGRCT?^JXRT^p@jBd@rALLRFLZVx@fBnEdAvCr@~Bh@vB\\fBb@dCPnAGl@?XZ~EHjBEl@B~B?x@Al@EdAQ`BS~@IVIXm@rAW\\{@bAuCvCeBhB_@l@m@f@gAx@qAt@kE~AmEdBg@Po@PKJWJODaAVoBb@yDh@eBLcK~@kD^qCn@i@Pc@LMRiC`A{At@yChB}KfHgDrBuBfAgDrAqDxAsBjAa@X{@p@_C|BwAlB}AlCgAbC{A~D_AjBgBpC_AfAsBlBmAv@uA|@{FbCuGxCsCzAw@d@_B|@gCfBkLxHcLvHkSfN}B|AaChBqEnD_A~@{BpCmB|C}DtHiEbIcBbDg@v@gAjAwAjAw@b@y@\\eAT{HxAsA\\oAl@i@`@o@p@o@|@k@vAc@dBG`@E~@SpCk@fGe@`DYlBKnAOrAAh@mD~ZoAnLS~AwGfk@iAdJKtA_AxHq@dFoAlI{ErV}CfQyBtKiGn[gBlJ}AhIW\\Qt@u@dD{@nCq@vAk@p@mArAg@b@_@`@OHE@]f@u@`AWNe@DkAM[EM@IBOC_@G_AGsCGsCB{BL_C^wBh@yAf@mAh@`BvHp@zCFZZKp@YXOVS\\[FH`@j@XLF_@CAKMAOb@e@n@_@v@S",
      "distance": "8.8 mi",
      "eta": "13 mins",
      "raw_directions": {
        "status": "OK",
        "routes": [
          {
            "bounds": {
              "northeast": {
                "lat": 37.3926107,
                "lng": -121.8774402
              },
              "southwest": {
                "lat": 37.3249114,
                "lng": -121.979107
              }
            },
            "copyrights": "Map data \ufffd\ufffd2014 Google",
            "legs": [
              {
                "distance": {
                  "text": "8.8 mi",
                  "value": 14187
                },
                "duration": {
                  "text": "13 mins",
                  "value": 753
                },
                "steps": [
                  {
                    "distance": {
                      "text": "0.3 mi",
                      "value": 554
                    },
                    "duration": {
                      "text": "2 mins",
                      "value": 97
                    },
                    "polyline": {
                      "points": "_{zbF|x{fVdBmAHEjAw@nAy@hAu@lAw@jCkBhH}E"
                    },
                    "maneuver": null,
                    "end_location": {
                      "lat": 37.3301083,
                      "lng": -121.8774402
                    },
                    "start_location": {
                      "lat": 37.3344009,
                      "lng": -121.8806304
                    },
                    "travel_mode": "DRIVING",
                    "html_instructions": "Head <b>southeast<\/b> on <b>S 7th St<\/b> toward <b>E San Salvador St<\/b>"
                  },
                  {
                  /* ... */
                  }
                ],
                "end_address": "2813 Mission College Boulevard, Santa Clara, CA 95054, USA",
                "start_address": "200 South 7th Street, San Jos\ufffd\ufffd State University, San Jose, CA 95112, USA",
                "end_location": {
                  "lat": 37.3900517,
                  "lng": -121.9781716
                },
                "start_location": {
                  "lat": 37.3344009,
                  "lng": -121.8806304
                }
              }
            ],
            "summary": "CA-87 N",
            "overview_polyline": {
              "points": "_{zbF|x{fVjGeEvCmBtLiIfEhLfBrEVW\\SPGRCT?^JXRT^p@jBd@rALLRFLZVx@fBnEdAvCr@~Bh@vB\\fBb@dCPnAGl@?XZ~EHjBEl@B~B?x@Al@EdAQ`BS~@IVIXm@rAW\\{@bAuCvCeBhB_@l@m@f@gAx@qAt@kE~AmEdBg@Po@PKJWJODaAVoBb@yDh@eBLcK~@kD^qCn@i@Pc@LMRiC`A{At@yChB}KfHgDrBuBfAgDrAqDxAsBjAa@X{@p@_C|BwAlB}AlCgAbC{A~D_AjBgBpC_AfAsBlBmAv@uA|@{FbCuGxCsCzAw@d@_B|@gCfBkLxHcLvHkSfN}B|AaChBqEnD_A~@{BpCmB|C}DtHiEbIcBbDg@v@gAjAwAjAw@b@y@\\eAT{HxAsA\\oAl@i@`@o@p@o@|@k@vAc@dBG`@E~@SpCk@fGe@`DYlBKnAOrAAh@mD~ZoAnLS~AwGfk@iAdJKtA_AxHq@dFoAlI{ErV}CfQyBtKiGn[gBlJ}AhIW\\Qt@u@dD{@nCq@vAk@p@mArAg@b@_@`@OHE@]f@u@`AWNe@DkAM[EM@IBOC_@G_AGsCGsCB{BL_C^wBh@yAf@mAh@`BvHp@zCFZZKp@YXOVS\\[FH`@j@XLF_@CAKMAOb@e@n@_@v@S"
            }
          }
        ]
      }
    }
  ]
}
```

<a name="apn-ws"></a>APN (Apple Push Notification) Web Service
------------------------------------------

This webservice is to send Apple Push Notification to an iOS device. **All parameters are optional**

| URI                                           |  Method    | RESPONSE    | Sample URI                                                                           | Notes          |
|-----------------------------------------------|------------|-------------|--------------------------------------------------------------------------------------|----------------|
|/v1/apn?badge={int}&token={str}&message={str}&send=true  | GET        | Status JSON | /v1/apn?badge=2&token=AAAAA+BBBBB+CCCCC+DDDDD+EEEEE+FFFFF&message=Traffic+on+101+N.  | **send** - true = send, otherwise message will not be send to the device, **token** - unique device token, **message** - message to be sent to the device, **badge** - is a notfication icon                |


```json
{
    "message": "Traffic on 101 N.",
    "time": "Mon Apr 3 20:38:45 PDT 2014",
    "token": "AAAAA BBBBB CCCCC DDDDD EEEEE FFFFF",
    "badge": 2
}
```


<a name="find-friends-in-the-area"> </a>Find Friends In the Area
------------------------------------------

This webservice is to finds all history locations in the database for a given circle AND between start and end time AND user

URI: /v2/geo/friends-around?{PARAMETERS}

| Parameter         | Description                       |
|-------------------|-----------------------------------|
| latitude          | Latitude                          |
| longitude         | Longitude                         |
| radius-in-meters  | Radius to search around in meters |
| start-time        | Start time in milliseconds        |
| end-time          | End time in milliseconds          |
| friends           | ID(s) of friends                  |


**Sample Request**
`/v2/geo/friends-around?latitude=37.336639404296875&longitude=-121.88200378417969&radius-in-meters=20000&start-time=0&end-time=1497701769468&friends=demo`

**Response**
JSON of `HashMap<UserId, List<GeoHistory>`

**Sample Response**

```
{
 "demo":
    [
        {
            "id":"534f3c31e4b0ec2b427c9479",
            "timestamp":1397701679400,
            "position":[ 37.33845901489258, -121.88189697265625],
            "userName":"demo",
            "historyType":null
        },

        {
            "id":"534f3c8be4b0ec2b427c947a",
            "timestamp":1397701769468,
            "position":[ 37.336639404296875, -121.88200378417969],
            "userName":"demo",
            "historyType":null
        }
    ]
}
```

<a name="find-all-geo-locations-for"></a>Find all geo locations for user for a particular type
------------------------------------------------------

This web service will find all geo locations for a give user and a give geo location type

URI: `/v2/geo/typed-location?uid=100008250583323&type=CHECKIN`


| Parameter | Is required   | Description                       |
|-----------|---|-----------------------------------|
| uid       | Yes   | 100008250583323                   |
| type      | No, Default value is `null`  | `CHECKIN` = User checked-in, `CHECKOUT` - User checked-out, `null` - Regular location point |

**Sample request**
`/v2/geo/typed-location?uid=1389900341294972`
`/v2/geo/typed-location?uid=1389900341294972&type=CHECKIN`

**Sample response**

```
[
    {"id":"534f3c31e4b0ec2b427c9479","timestamp":1397701679400,"position":[37.33845901489258,-121.88189697265625],"userName":"1389900341294972","historyType":null,"yelpId":null},
    {"id":"534f3c8be4b0ec2b427c947a","timestamp":1397701769468,"position":[37.336639404296875,-121.88200378417969],"userName":"1389900341294972","historyType":null,"yelpId":null},
    {"id":"534f3cc5e4b0ec2b427c947b","timestamp":1397701826521,"position":[37.33686828613281,-121.88200378417969],"userName":"1389900341294972","historyType":null,"yelpId":null},
    {"id":"534f3ccae4b0ec2b427c947c","timestamp":1397701833319,"position":[37.338050842285156,-121.8822021484375],"userName":"1389900341294972","historyType":null,"yelpId":null},
    {"id":"534f3cd7e4b0ec2b427c947d","timestamp":1397701845871,"position":[37.33884048461914,-121.88259887695312],"userName":"1389900341294972","historyType":null,"yelpId":null},
    {"id":"534f3cece4b0ec2b427c947e","timestamp":1397701856079,"position":[37.339149475097656,-121.88269805908203],"userName":"1389900341294972","historyType":null,"yelpId":null}
]
```
