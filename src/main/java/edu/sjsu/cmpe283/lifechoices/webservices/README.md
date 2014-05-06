API for the all available Web Services


- [Get User Details](#get-user-details)
- [Refresh User Friends](#refresh-user-friends)
- [New geo location V1](#new-geo-location)
- [New geo location V2 with Check In/Out](#new-geo-loc-v2)
- [User Location History](#user-loc-his)
- [Google Places Service](#google-places-service) - Google Places API
- [Yelp Service](#yelp-service) - Yelp API
- [Yelp Service with user info](#yelp-service-with-user-info) - Yelp API
- [User Updates](#user-update)
- [New User Updates](#user-update-v2)
- [APN (Apple Push Notification) Web Service](#apn-ws) - Apple Push Notification
- [Find Friends In the Area](#find-friends-in-the-area)
- [Find all geo locations for user for a particular type](#find-all-geo-locations-for)
- [Find user's most visited place](#most-visited-place)
- [Search Service](#search-service) - Wolfram Alpha API
- [Voice Search Service](#voice-search-service) - Voice-2-Text + Wolfram Alpha API
- [Comprehensive Voice Search Service](#comprehensive-voice-search-service) - Voice-2-Text + Yelp + Google Places + Google Map (traffic) + Open Weather
- [Voice Search Service](#voice-to-text)
- [Events using Meetup](#v2-events) - Meetup API


<a name="get-user-details"></a>Get User Details
----------------

This web service is to get user details by using user's Social Network ID

URI: **GET /v1/user?uid={userid}** (userid = Social Network Id)

Response type: **application/json**


<a name="refresh-user-friends"></a>Refresh User Friends
--------------------
This web service is to update current user friends list. Usually this is done everything on the client when list of friends is refreshed.

URI: **POST /v1/user?uid={userid}&friends=fid1,fid2,fid3,fid4,fid5** (userid = Social Network Id, friends = friends Social Network IDs)

**Body**: No body is expected

**Response**: User details JSON


<a name="new-geo-location"></a> New geo location V1
-----------------------------------------------------

URI: **POST /v1/geo/**

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


<a name="yelp-service-with-user-info"></a>Yelp Service with user info
------------------------------------------------------

Same as above but will return additional info about user visits.
New fields:

- user-id: {STRING}
- num-of-visits: {NUMBER}
- has-been-visited: {BOOLEAN}

**URI**

`/v1/food/user-details?keyword=Banana+Leaf+Restaurant&latitude=37.4287133&longitude=-121.9204447&userId=1389900341294972`

`/v1/events/user-details?keyword=Fun+Places&latitude=37.4287133&longitude=-121.9204447&userId=1389900341294972`

**Sample Response**

```
   {
      "total":19,
      "region":{
         "center":{
            "longitude":-121.911780887845,
            "latitude":37.43733442229005
         },
         "span":{
            "latitude_delta":0.04001804903811035,
            "longitude_delta":0.021892886740999984
         }
      },
      "user-id":"1389900341294972",
      "businesses":[
         {
            "rating_img_url_large":"http://s3-media3.ak.yelpcdn.com/assets/2/www/img/bd9b7a815d1b/ico/stars/v1/stars_large_3_half.png",
            "snippet_text":"Yuuum... great food.. always great food...\n\nSatay, salmon, roti, curry are just great.\n \nlong queue almost 90% time. book table before you reach there.",
            "phone":"4087199811",
            "rating_img_url":"http://s3-media1.ak.yelpcdn.com/assets/2/www/img/5ef3eb3cb162/ico/stars/v1/stars_3_half.png",
            "menu_date_updated":1387620347,
            "menu_provider":"single_platform",
            "review_count":1634,
            "location":{
               "state_code":"CA",
               "display_address":[
                  "182 Ranch Dr",
                  "Milpitas, CA 95035"
               ],
               "address":[
                  "182 Ranch Dr"
               ],
               "postal_code":"95035",
               "country_code":"US",
               "city":"Milpitas"
            },
            "is_closed":false,
            "is_claimed":true,
            "rating_img_url_small":"http://s3-media1.ak.yelpcdn.com/assets/2/www/img/2e909d5d3536/ico/stars/v1/stars_small_3_half.png",
            "url":"http://www.yelp.com/biz/banana-leaf-restaurant-milpitas",
            "id":"banana-leaf-restaurant-milpitas",
            "distance":71.1508684582253,
            "image_url":"http://s3-media1.ak.yelpcdn.com/bphoto/uvvN8OyvzSXAKOtb9HEhPw/ms.jpg",
            "name":"Banana Leaf Restaurant",
            "display_phone":"+1-408-719-9811",
            "mobile_url":"http://m.yelp.com/biz/banana-leaf-restaurant-milpitas",
            "snippet_image_url":"http://s3-media1.ak.yelpcdn.com/photo/UaihzCcGDAsjBCzzLEXE8Q/ms.jpg",
            "categories":[
               [
                  "Asian Fusion",
                  "asianfusion"
               ],
               [
                  "Thai",
                  "thai"
               ]
            ],
            "rating":3.5,
            "num-of-visits":1,
            "has-been-visited":true
         },
         {
            "rating_img_url_large":"http://s3-media2.ak.yelpcdn.com/assets/2/www/img/ccf2b76faa2c/ico/stars/v1/stars_large_4.png",
            "phone":"4082636788",
            "snippet_text":"Great lunch spot in the Milpitas area.  Ample parking, usually not too crowded until 12:30ish.\n\nSolid Malaysian cuisine, excellent flavors (on the...",
            "rating_img_url":"http://s3-media4.ak.yelpcdn.com/assets/2/www/img/c2f3dd9799a5/ico/stars/v1/stars_4.png",
            "review_count":494,
            "location":{
               "state_code":"CA",
               "display_address":[
                  "181 W Calaveras Blvd",
                  "Milpitas, CA 95035"
               ],
               "address":[
                  "181 W Calaveras Blvd"
               ],
               "postal_code":"95035",
               "country_code":"US",
               "city":"Milpitas"
            },
            "is_closed":false,
            "is_claimed":true,
            "rating_img_url_small":"http://s3-media4.ak.yelpcdn.com/assets/2/www/img/f62a5be2f902/ico/stars/v1/stars_small_4.png",
            "url":"http://www.yelp.com/biz/layang-layang-milpitas",
            "id":"layang-layang-milpitas",
            "distance":814.3213004365476,
            "image_url":"http://s3-media2.ak.yelpcdn.com/bphoto/WUJQ-0GwdHhRDPxKZ6uU5w/ms.jpg",
            "name":"Layang Layang",
            "display_phone":"+1-408-263-6788",
            "mobile_url":"http://m.yelp.com/biz/layang-layang-milpitas",
            "snippet_image_url":"http://s3-media3.ak.yelpcdn.com/photo/kwcBFR46CQYwofsjZlOVCQ/ms.jpg",
            "categories":[
               [
                  "Malaysian",
                  "malaysian"
               ]
            ],
            "rating":4.0,
            "num-of-visits":0,
            "has-been-visited":false
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

<a name="user-update-v2"></a>User Updates V2
----------------------
| URI                                                                                       |  Method    | RESPONSE       | Sample URI                                           |
|-------------------------------------------------------------------------------------------|------------|----------------|------------------------------------------------------|
|/v2/updates?maps={boolean}latitude={double}&longitude={double}&width={int}&height={height} Defaults to SJSU, 640x480              | GET        | List of Map/ETA/Distance/Weather for places | /v1/food?latitude=-33.8665433&longitude=151.1956316&width=320&240  |


Response type: **application/json**

**Sample Response**:

```json
{

    "latitude": 37.334679,
    "longitude": -121.881113,
    "weather": {
        "iconURL": "http://openweathermap.org/img/w/",
        "conditions": {
            "dt": 1399091808,
            "name": "San Jose",
            "weather": [
                {
                    "id": 802,
                    "main": "Clouds",
                    "description": "scattered clouds",
                    "icon": "03n"
                }
            ],
            "main": {
                "temp": 289.03,
                "pressure": 1017,
                "humidity": 72,
                "temp_min": 286.48,
                "temp_max": 290.37
            },
            "wind": {
                "speed": 1.16,
                "gust": null,
                "deg": 302
            },
            "rain": null,
            "clouds": {
                "all": 36
            },
            "coord": {
                "lat": 37.33,
                "lon": -121.88
            },
            "sys": {
                "sunrise": 1399122558,
                "sunset": 1399172360
            }
        },
        "forecast": {
            "cod": "200",
            "city": {
                "id": 5331587,
                "name": "Buena Vista",
                "country": "US",
                "coord": {
                    "lat": 37.321331,
                    "lon": -121.916618
                }
            },
            "cnt": 3,
            "list": [
                {
                    "temp": {
                        "day": 68.99,
                        "min": 39.24,
                        "max": 68.99,
                        "night": 39.24,
                        "eve": 57.63,
                        "morn": 68.99
                    },
                    "pressure": 991.95,
                    "humidity": 24,
                    "weather": [
                        {
                            "id": 800,
                            "main": "Clear",
                            "description": "sky is clear",
                            "icon": "02n"
                        }
                    ],
                    "speed": 4.45,
                    "deg": 289,
                    "clouds": 8,
                    "dt": 1399060800
                }
                /* , ... */
            ]
        }
    },
    "destinations": [
        {
            "latitude": 37.412985,
            "longitude": -122.053472,
            "weather": {
                "iconURL": "http://openweathermap.org/img/w/",
                "conditions": {
                    "dt": 1399093226,
                    "name": "Mountain View",
                    "weather": [
                        {
                            "id": 802,
                            "main": "Clouds",
                            "description": "scattered clouds",
                            "icon": "03n"
                        }
                    ],
                    "main": {
                        "temp": 288.39,
                        "pressure": 1017,
                        "humidity": 73,
                        "temp_min": 285.37,
                        "temp_max": 291.48
                    },
                    "wind": {
                        "speed": 2.76,
                        "gust": null,
                        "deg": 327
                    },
                    "rain": null,
                    "clouds": {
                        "all": 44
                    },
                    "coord": {
                        "lat": 37.41,
                        "lon": -122.05
                    },
                    "sys": {
                        "sunrise": 1399122590,
                        "sunset": 1399172410
                    }
                },
                "forecast": {
                    "cod": "200",
                    "city": {
                        "id": 5375480,
                        "name": "Mountain View",
                        "country": "US",
                        "coord": {
                            "lat": 37.386051,
                            "lon": -122.083847
                        }
                    },
                    "cnt": 3,
                    "list": [
                        {
                            "temp": {
                                "day": 67.15,
                                "min": 47.75,
                                "max": 67.15,
                                "night": 47.75,
                                "eve": 67.15,
                                "morn": 67.15
                            },
                            "pressure": 992.02,
                            "humidity": 30,
                            "weather": [
                                {
                                    "id": 801,
                                    "main": "Clouds",
                                    "description": "few clouds",
                                    "icon": "02n"
                                }
                            ],
                            "speed": 3.37,
                            "deg": 293,
                            "clouds": 20,
                            "dt": 1399060800
                        },
                        {
                            "temp": {
                                "day": 69.21,
                                "min": 47.46,
                                "max": 69.21,
                                "night": 47.46,
                                "eve": 57.52,
                                "morn": 51.73
                            },
                            "pressure": 990.78,
                            "humidity": 38,
                            "weather": [
                                {
                                    "id": 803,
                                    "main": "Clouds",
                                    "description": "broken clouds",
                                    "icon": "04d"
                                }
                            ],
                            "speed": 3.26,
                            "deg": 285,
                            "clouds": 76,
                            "dt": 1399147200
                        },
                        {
                            "temp": {
                                "day": 68.5,
                                "min": 46.89,
                                "max": 68.5,
                                "night": 46.89,
                                "eve": 59.58,
                                "morn": 51.76
                            },
                            "pressure": 991.98,
                            "humidity": 49,
                            "weather": [
                                {
                                    "id": 800,
                                    "main": "Clear",
                                    "description": "sky is clear",
                                    "icon": "01d"
                                }
                            ],
                            "speed": 4.04,
                            "deg": 290,
                            "clouds": 0,
                            "dt": 1399233600
                        }
                    ]
                }
            },
            "maps_url": null,
            "distance": null,
            "eta": null,
            "raw_directions": null
        }
        /* , ... */
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

URI: `/v2/geo/typed-location?uid={user_id}&type={type}`


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


<a name="most-visited-place"></a>Find user's most visited place
------------------------------------------------------

This web service will find the most visited place user visited.

URI: `/v2/geo/most-visited-place?uid={user_id}`

| Parameter | Is required   | Description                       |
|-----------|---|-----------------------------------|
| uid       | Yes   | 100008250583323                   |


URI: `/v2/geo/most-visited-place?uid=1389900341294972`

**Sample response**

```
{
   "is_claimed":true,
   "rating":3.5,
   "mobile_url":"http://m.yelp.com/biz/banana-leaf-restaurant-milpitas",
   "rating_img_url":"http://s3-media1.ak.yelpcdn.com/assets/2/www/img/5ef3eb3cb162/ico/stars/v1/stars_3_half.png",
   "review_count":1634,
   "name":"Banana Leaf Restaurant",
   "snippet_image_url":"http://s3-media1.ak.yelpcdn.com/photo/UaihzCcGDAsjBCzzLEXE8Q/ms.jpg",
   "rating_img_url_small":"http://s3-media1.ak.yelpcdn.com/assets/2/www/img/2e909d5d3536/ico/stars/v1/stars_small_3_half.png",
   "url":"http://www.yelp.com/biz/banana-leaf-restaurant-milpitas",
   "menu_date_updated":1387620347,
   "reviews":[
      {
         "rating":5,
         "excerpt":"Yuuum... great food.. always great food...\n\nSatay, salmon, roti, curry are just great.\n \nlong queue almost 90% time. book table before you reach there.",
         "time_created":1397776566,
         "rating_image_url":"http://s3-media1.ak.yelpcdn.com/assets/2/www/img/f1def11e4e79/ico/stars/v1/stars_5.png",
         "rating_image_small_url":"http://s3-media1.ak.yelpcdn.com/assets/2/www/img/c7623205d5cd/ico/stars/v1/stars_small_5.png",
         "user":{
            "image_url":"http://s3-media1.ak.yelpcdn.com/photo/UaihzCcGDAsjBCzzLEXE8Q/ms.jpg",
            "id":"Al1ClJ4HU6qUe-zpYAkBTA",
            "name":"San P."
         },
         "rating_image_large_url":"http://s3-media3.ak.yelpcdn.com/assets/2/www/img/22affc4e6c38/ico/stars/v1/stars_large_5.png",
         "id":"wsLuFOKfGw0kAqHBM2AtPQ"
      }
   ],
   "phone":"4087199811",
   "snippet_text":"Yuuum... great food.. always great food...\n\nSatay, salmon, roti, curry are just great.\n \nlong queue almost 90% time. book table before you reach there.",
   "image_url":"http://s3-media1.ak.yelpcdn.com/bphoto/uvvN8OyvzSXAKOtb9HEhPw/ms.jpg",
   "categories":[
      [
         "Asian Fusion",
         "asianfusion"
      ],
      [
         "Thai",
         "thai"
      ]
   ],
   "display_phone":"+1-408-719-9811",
   "rating_img_url_large":"http://s3-media3.ak.yelpcdn.com/assets/2/www/img/bd9b7a815d1b/ico/stars/v1/stars_large_3_half.png",
   "menu_provider":"single_platform",
   "id":"banana-leaf-restaurant-milpitas",
   "is_closed":false,
   "location":{
      "city":"Milpitas",
      "display_address":[
         "182 Ranch Dr",
         "Milpitas, CA 95035"
      ],
      "postal_code":"95035",
      "country_code":"US",
      "address":[
         "182 Ranch Dr"
      ],
      "state_code":"CA"
   }
}
```


<a name="search-service"></a>Search Service
------------------------------------------------------

This web service will perform search against Wolfram Alpha DB and return search results in image format.

There are two different types of searches: raw and formatted.

URI: `GET /v1/search?q={search_query}&raw={true or false, default false}&uid=1389900341294972`


| Parameter | Is required   | Description                       |
|-----------|---------------|-----------------------------------|
| q         | Yes           | Text search query                   |
| uid       | No            | User Id to associate this search with a user |
| raw       | No            | If true then will return raw json, otherwise json will be formatted.|

**Response Samples**

- [Formatted](https://github.com/cmpe273-indexzero/life-choices-WS/blob/master/src/main/resources/wolframalpharesponse-formatted.json)
- [Raw](https://github.com/cmpe273-indexzero/life-choices-WS/blob/master/src/main/resources/wolframalpharesponse-raw.json)


<a name="voice-search-service"></a>Voice Search Service
------------------------------------------------------
This web service will perform search against Wolfram Alpha DB using audio file with a search phrase and return search results in image format.

Similarly to the text search, this web service can return two different types of the output, raw or formatted. Default is formatted.

URI: `POST /v1/search?q-voice={multipart/form-data}&raw={true or false, default false}&uid=1389900341294972`

| Parameter | Is required   | Description                       |
|-----------|---------------|-----------------------------------|
| q-voice   | Yes           | Audio file with a search query. See below [supported audio formats](#audio-file-supp)  |
| uid       | No            | User Id to associate this search with a user |
| raw       | No            | If true then will return raw json, otherwise json will be formatted.|

<a name="audio-file-supp"></a>The supported audio formats are:

- 16-bit PCM WAV, linear coding, **single channel**, 8 kHz sampling
- 16-bit PCM WAV, ulaw coding, **single channel**, 8 kHz sampling
- 16-bit PCM WAV, linear coding, **single channel**, 16 kHz sampling
- 16-bit PCM WAV, ulaw coding, **single channel**, 16 kHz sampling
- AMR (narrowband), 12.2 kbit/s, 8 kHz sampling
- AMR-WB (wideband), 12.65 kbit/s, 16khz sampling
- OGG, speex encoding, 8kHz sampling
- OGG, speex encoding, 16kHz sampling
- Raw, linear coding, little-endian byte order, 8kHz sampling
- Raw, linear coding, big-endian byte order, 8kHz sampling
- Raw, ulaw coding, little-endian byte order, 8kHz sampling
- Raw, ulaw coding, big-endian byte order, 8kHz sampling
- Raw, linear coding, little-endian byte order, 16kHz sampling
- Raw, linear coding, big-endian byte order, 16kHz sampling
- Raw, ulaw coding, little-endian byte order, 16kHz sampling
- Raw, ulaw coding, big-endian byte order, 16kHz sampling

[Sample page to try this search](http://54.193.8.183:9999/voice-to-text-text.html). Use [ALPHABET](https://github.com/cmpe273-indexzero/life-choices-WS/blob/master/src/main/resources/alphabet.wav) or [TIME](https://github.com/cmpe273-indexzero/life-choices-WS/blob/master/src/main/resources/what-time-is-it-right-now.wav) sample files for the upload


**Response Samples**

- [Formatted](https://github.com/cmpe273-indexzero/life-choices-WS/blob/master/src/main/resources/wolframalpharesponse-formatted.json)
- [Raw](https://github.com/cmpe273-indexzero/life-choices-WS/blob/master/src/main/resources/wolframalpharesponse-raw.json)


<a name="voice-to-text"></a>Voice Search Service
------------------------------------------------------
This web service will transcribe voice to text.

URI: `POST /v1/voice?raw={true or false, default false}&uid=1389900341294972`

Form file parameter name (`multipart/form-data`): `q-voice`

Response will be JSON with the transcibed text inside



<a name="comprehensive-voice-search-service"></a>Comprehensive Voice Search Service
------------------------------------------------------
This web service will perform search against Wolfram Alpha DB using audio file with a search phrase and return search results in image format.

URI: `POST /v1/voice/search?uid=1389900341294972&latitude={xxxxxxxx}&longitude={yyyyyyy}&radius={zzz}`

| Parameter                     | Is required                           | Description                       |
|-------------------------------|---------------------------------------|-----------------------------------|
| q-voice (form parameter)      | Yes                                   | Audio file with a search query. See below [supported audio formats](#audio-file-supp)  |
| uid                           | No (default is Rob = 1389900341294972 | User Id to associate this search with a user |
| latitude                      | No (default is SJSU = 37.334679)      | |
| longitude                     | No (default is SJSU = -121.881113)    | |
| radius                        | No (default = 1610 * 3 (meters in three miles) )               | distance in miles to retrieve.|


**Response will be JSON with the following properties:**

- yelp-food
- yelp-events
- google-food
- google-events
- weather-traffic

**Voice Commands**

| Word(s)                       | Description                                                               |
|-------------------------------|---------------------------------------------------------------------------|
| **deal**                      | Will enable deals search if this word is present in the command           |
| **event** or **thing** or **todo** or **to do**                    | Will enable events search in Google Places and Yelp                       |
| **food** or **restaurant**    | Will enable food search in Google Places and Yelp                         |
| **weather** or **traffic**    | Will enable weather and traffic search in Open Weather and Google Maps    |



<a name="v2-events"></a>Events
------------------------------------------------------

Retrieve events using the Meet up API

URI: `/v2/events`


| Parameter | Is required   | Description  |
|-----------|---|--------------------------|
| latitude  | Yes, Defaults to SJSU  |  |
| longitude | Yes, Defaults to SJSU  |  |
| category  | no, Defaults to `null` | list of comma delimited categories  |
| page 		| no, defaults to 20  | # of events to retrieve  |
| radius 	| no | distance in miles to retrieve events  |

**Sample request**

`/v2/events/`

`/v2/events/?latitude=37.33845901489258&longitude=-121.88189697265625=page=50`

`/v2/events/categories`

**Sample response**

```json
{
    "results": [
        {
            "status": "upcoming",
            "visibility": "public",
            "maybe_rsvp_count": 0,
            "venue": {
                "id": 4887182,
                "zip": "95054",
                "lon": -121.982994,
                "repinned": false,
                "name": "AMC Mercado 20",
                "state": "CA",
                "address_1": "3111 Mission College Blvd",
                "lat": 37.388851,
                "city": "Santa Clara",
                "country": "us"
            },
            "id": "170118502",
            "utc_offset": -28800000,
            "distance": 6.745843410491943,
            "duration": 17100000,
            "time": 1450494000000,
            "waitlist_count": 0,
            "updated": 1398995166000,
            "yes_rsvp_count": 9,
            "created": 1394262588000,
            "event_url": "http://www.meetup.com/timetoeat/events/170118502/",
            "description": "<p>The Stars Wars Saga continues...Episode VII</p> <p>May The Force Be With You!</p> <p><br/><a href=\"http://starwars.com/\" class=\"linkified\">http://starwars.com/</a></p> <p>Movie Time: 8:15pm IMAX 3D</p> <p>Movie Theater: AMC Mercado 20<br/>3111 Mission College Blvd., Santa Clara, CA 95054</p> <p>Please buy your ticket in advance through Fandango once the movie show times are being announced.</p> <p><br/><a href=\"http://www.fandango.com/?full=1\" class=\"linkified\">http://www.fandango.com/?full=1</a></p> <p><br/><a href=\"http://mobile.fandango.com/home?&amp;from=inter_iphone&amp;aid=HAVEITLINK\" class=\"linkified\">http://mobile.fandango.com/home?&amp;from=inter_iphone&amp;aid=HAVEITLINK</a></p> <p><br/><a href=\"http://www.AMCTheatres.com/Mercado/\" class=\"linkified\">http://www.AMCTheatres.com/Mercado/</a></p> <p>Meeting Place: To Be Discussed</p> <p>Meeting Time: 7pm</p> <p><a href=\"http://m.imdb.com/title/tt2488496/\" class=\"linkified\">http://m.imdb.com/title/tt2488496/</a></p>",
            "name": "Star Wars: Episode VII",
            "headcount": 0,
            "group": {
                "id": 12406222,
                "group_lat": 37.349998474121094,
                "name": "(????)? Time to EAT and Make New Friends!!",
                "group_lon": -121.95999908447266,
                "join_mode": "approval",
                "urlname": "timetoeat",
                "who": "Foodies"
            }
        } /*, ... */
    ],
    "meta": {
        "lon": -121.881113,
        "count": 20,
        "link": "https://api.meetup.com/2/open_events",
        "next": "https://api.meetup.com/2/open_events?key=58ae7356571b50122529115542274&status=upcoming&radius=25.0&and_text=False&limited_events=False&desc=true&offset=1&format=json&lat=37.334679&page=20&lon=-121.881113",
        "total_count": 1845,
        "url": "https://api.meetup.com/2/open_events?key=58ae7356571b50122529115542274&status=upcoming&radius=25.0&and_text=False&limited_events=False&desc=true&offset=0&format=json&lat=37.334679&page=20&lon=-121.881113",
        "id": "",
        "title": "Meetup Open Events v2",
        "updated": 1399182723829,
        "description": "Searches for recent and upcoming public events hosted by Meetup groups. Its search window is the past one month through the next three months, and is subject to change. Open Events is optimized to search for current events by location, category, topic, or text, and only lists Meetups that have **3 or more RSVPs**. The number or results returned with each request is not guaranteed to be the same as the page size due to secondary filtering. If you're looking for a particular event or events within a particular group, use the standard [Events](/meetup_api/docs/2/events/) method.",
        "method": "OpenEvents",
        "lat": 37.334679
    }
}
```
