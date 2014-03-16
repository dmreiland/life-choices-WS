Services (Business Layer)
=========================

These classes responsible for the business logic. They can be treated as business classes

Google Places Services (GooglePlacesServices)
---------------------------------------------

Google API Key: *AIzaSyCtqOYumCWBcjHH2ixhts9EL5BiPaXoH6s*

**IMPORTANT**: Need to allow your IP in [THE CONSOLE](https://console.developers.google.com/project/apps~life-choices/apiui/credential) for Google Places API to be called

Official Google Doc for this library can be found [HERE](https://code.google.com/p/places-api-client/)

| URI                                                                                                               |  Method    | RESPONSE       | Sample URI |
|-------------------------------------------------------------------------------------------------------------------|------------|----------------|------------|
|/v1/places/{latitude}/{longitude}?radiusinmeters={integer : default=500}&type={string : if empty will return all } | GET        | List of places | /v1/places/-33.8665433/151.1956316?radiusinmeters=500&type=park |
|/v1/places/types                                                                                                   | GET        | List of ALL Place Types | |

###Google Places Types ( [Source](https://developers.google.com/places/documentation/supported_types) )

accounting
airport
amusement_park
aquarium
art_gallery
atm
bakery
bank
bar
beauty_salon
bicycle_store
book_store
bowling_alley
bus_station
cafe
campground
car_dealer
car_rental
car_repair
car_wash
casino
cemetery
church
city_hall
clothing_store
convenience_store
courthouse
dentist
department_store
doctor
electrician
electronics_store
embassy
establishment
finance
fire_station
florist
food
funeral_home
furniture_store
gas_station
general_contractor
grocery_or_supermarket
gym
hair_care
hardware_store
health
hindu_temple
home_goods_store
hospital
insurance_agency
jewelry_store
laundry
lawyer
library
liquor_store
local_government_office
locksmith
lodging
meal_delivery
meal_takeaway
mosque
movie_rental
movie_theater
moving_company
museum
night_club
painter
park
parking
pet_store
pharmacy
physiotherapist
place_of_worship
plumber
police
post_office
real_estate_agency
restaurant
roofing_contractor
rv_park
school
shoe_store
shopping_mall
spa
stadium
storage
store
subway_station
synagogue
taxi_stand
train_station
travel_agency
university
veterinary_care
zoo

