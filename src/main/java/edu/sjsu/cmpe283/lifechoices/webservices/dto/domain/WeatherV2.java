package edu.sjsu.cmpe283.lifechoices.webservices.dto.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WeatherV2 {
    // DETAILS: http://bugs.openweathermap.org/projects/api/wiki/Weather_Data
    
    public String iconURL = "http://openweathermap.org/img/w/";
    Conditions conditions;
    Forecast forecast;
    
    
    public String getIconURL() {
        return iconURL;
    }
    
    public Conditions getConditions() {
        return conditions;
    }

    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }
    
    public Forecast getForecast() {
        return forecast;
    }
    
    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }


    
    

    public static class Conditions {
        //@JsonProperty("dt")
        long dt;
        String name;
        @JsonProperty("coord")
        Coordinates coordinates;
        @JsonProperty("sys")
        System system;
        List<WeatherData> weather;
        MainData main;
        Wind wind;
        Rain rain;
        Clouds clouds;
        
        public long getDt() {
            return dt;
        }
        
        public void setDt(long dt) {
            this.dt = dt;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public Coordinates getCoordinates() {
            return coordinates;
        }
        
        public void setCoordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
        }
        
        public System getSystem() {
            return system;
        }
        
        public void setSystem(System system) {
            this.system = system;
        }
        
        public List<WeatherData> getWeather() {
            return weather;
        }
        
        public void setWeather(List<WeatherData> weather) {
            this.weather = weather;
        }
        
        public MainData getMain() {
            return main;
        }
        
        public void setMain(MainData main) {
            this.main = main;
        }
        
        public Wind getWind() {
            return wind;
        }
        
        public void setWind(Wind wind) {
            this.wind = wind;
        }
        
        public Rain getRain() {
            return rain;
        }
        
        public void setRain(Rain rain) {
            this.rain = rain;
        }
        
        public Clouds getClouds() {
            return clouds;
        }
        
        public void setClouds(Clouds clouds) {
            this.clouds = clouds;
        }
    }
    
    public static class Coordinates {
        Double lat;
        Double lon;
        
        public Double getLat() {
            return lat;
        }
        
        public void setLat(Double lat) {
            this.lat = lat;
        }
        
        public Double getLon() {
            return lon;
        }
        
        public void setLon(Double lon) {
            this.lon = lon;
        }
        
    }
    
    
    public static class System {
        Long sunrise;
        Long sunset;
        
        public Long getSunrise() {
            return sunrise;
        }
        
        public void setSunrise(Long sunrise) {
            this.sunrise = sunrise;
        }
        
        public Long getSunset() {
            return sunset;
        }
        
        public void setSunset(Long sunset) {
            this.sunset = sunset;
        }
    }
    
    
    public static class WeatherData {
        Long id;
        String main;
        String description;
        String icon;
        
        public Long getId() {
            return id;
        }
        
        public void setId(Long id) {
            this.id = id;
        }
        
        public String getMain() {
            return main;
        }
        
        public void setMain(String main) {
            this.main = main;
        }
        
        public String getDescription() {
            return description;
        }
        
        public void setDescription(String description) {
            this.description = description;
        }
        
        public String getIcon() {
            return icon;
        }
        
        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
    
    
    public static class MainData {
        Double temp;
        Long pressure;
        @JsonProperty("temp_min")
        Double tempMin;
        @JsonProperty("temp_max")
        Double tempMax;
        Double humidity;
        
        public Double getTemp() {
            return temp;
        }
        
        public void setTemp(Double temp) {
            this.temp = temp;
        }
        
        public Long getPressure() {
            return pressure;
        }
        
        public void setPressure(Long pressure) {
            this.pressure = pressure;
        }
        
        public Double getTempMin() {
            return tempMin;
        }
        
        public void setTempMin(Double tempMin) {
            this.tempMin = tempMin;
        }
        
        public Double getTempMax() {
            return tempMax;
        }
        
        public void setTempMax(Double tempMax) {
            this.tempMax = tempMax;
        }
        
        public Double getHumidity() {
            return humidity;
        }
        
        public void setHumidity(Double humidity) {
            this.humidity = humidity;
        }
    }
    
    
    
    public static class Wind {
        Double speed;
        Double gust;
        Integer deg;
        
        public Double getSpeed() {
            return speed;
        }
        
        public void setSpeed(Double speed) {
            this.speed = speed;
        }
        
        public Double getGust() {
            return gust;
        }
        
        public void setGust(Double gust) {
            this.gust = gust;
        }
        
        public Integer getDeg() {
            return deg;
        }
        
        public void setDeg(Integer deg) {
            this.deg = deg;
        }
    }
    
    public static class Rain {
        @JsonProperty("3h")
        Double rain;
        
        public Double getRain() {
            return rain;
        }
        
        public void setRain(Double rain) {
            this.rain = rain;
        }
    }
    
    public static class Clouds {
        Double all;
        
        
        public Double getAll() {
            return all;
        }
        
        public void setAll(Double all) {
            this.all = all;
        }
    }
    
    
    
    
    
    
    
    
    
    
    

    public static class Forecast {
        String cod;
        City city;
        Integer cnt;
        List<ForecastData> list;
        
        public String getCod() {
            return cod;
        }
        
        public void setCod(String cod) {
            this.cod = cod;
        }
        
        public City getCity() {
            return city;
        }
        
        public void setCity(City city) {
            this.city = city;
        }
        
        public Integer getCnt() {
            return cnt;
        }
        
        public void setCnt(Integer cnt) {
            this.cnt = cnt;
        }
        
        public List<ForecastData> getList() {
            return list;
        }
        
        public void setList(List<ForecastData> list) {
            this.list = list;
        }
    }
    
    
    public static class City {
        Long id;
        String name;
        String country;
        Coordinates coord;
        
        public Long getId() {
            return id;
        }
        
        public void setId(Long id) {
            this.id = id;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getCountry() {
            return country;
        }
        
        public void setCountry(String country) {
            this.country = country;
        }
        
        public Coordinates getCoord() {
            return coord;
        }
        
        public void setCoord(Coordinates coord) {
            this.coord = coord;
        }
    }
    
    public static class ForecastData {
        @JsonProperty("dt")
        long timeGMT;
        Temperature temp;
        Double pressure;
        Double humidity;
        List<WeatherData> weather;
        Double speed;
        Integer deg;
        Double clouds;
        
        public long getTimeGMT() {
            return timeGMT;
        }
        
        public void setTimeGMT(long timeGMT) {
            this.timeGMT = timeGMT;
        }
        
        public Temperature getTemp() {
            return temp;
        }
        
        public void setTemp(Temperature temp) {
            this.temp = temp;
        }
        
        public Double getPressure() {
            return pressure;
        }
        
        public void setPressure(Double pressure) {
            this.pressure = pressure;
        }
        
        public Double getHumidity() {
            return humidity;
        }
        
        public void setHumidity(Double humidity) {
            this.humidity = humidity;
        }
        
        public List<WeatherData> getWeather() {
            return weather;
        }
        
        public void setWeather(List<WeatherData> weather) {
            this.weather = weather;
        }
        
        public Double getSpeed() {
            return speed;
        }
        
        public void setSpeed(Double speed) {
            this.speed = speed;
        }
        
        public Integer getDeg() {
            return deg;
        }
        
        public void setDeg(Integer deg) {
            this.deg = deg;
        }
        
        public Double getClouds() {
            return clouds;
        }
        
        public void setClouds(Double clouds) {
            this.clouds = clouds;
        }
        
        
    }
    
    
    public static class Temperature {
        Double day;
        Double min;
        Double max;
        Double night;
        Double eve;
        Double morn;
        
        public Double getDay() {
            return day;
        }
        
        public void setDay(Double day) {
            this.day = day;
        }
        
        public Double getMin() {
            return min;
        }
        
        public void setMin(Double min) {
            this.min = min;
        }
        
        public Double getMax() {
            return max;
        }
        
        public void setMax(Double max) {
            this.max = max;
        }
        
        public Double getNight() {
            return night;
        }
        
        public void setNight(Double night) {
            this.night = night;
        }
        
        public Double getEve() {
            return eve;
        }
        
        public void setEve(Double eve) {
            this.eve = eve;
        }
        
        public Double getMorn() {
            return morn;
        }
        
        public void setMorn(Double morn) {
            this.morn = morn;
        }
    }
    
}
