package edu.sjsu.cmpe283.lifechoices.webservices.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.sjsu.cmpe283.lifechoices.webservices.dto.UpdatesDTO.Destinations;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.domain.WeatherV2;

public class UpdatesDTOV2 {
    @JsonProperty("latitude")
    Double latitude;
    @JsonProperty("longitude")
    Double longitude;
    @JsonProperty("weather")
    WeatherV2 weather;
    @JsonProperty("destinations")
    List<Destinations> destinations;
    
    
    
    
    
    public Double getLatitude() {
        return latitude;
    }

    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    
    public Double getLongitude() {
        return longitude;
    }

    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    
    public WeatherV2 getWeather() {
        return weather;
    }

    
    public void setWeather(WeatherV2 weather) {
        this.weather = weather;
    }

    
    public List<Destinations> getDestinations() {
        return destinations;
    }

    
    public void setDestinations(List<Destinations> destinations) {
        this.destinations = destinations;
    }

    
    public void addDesination(Destinations destination) {
        if(destinations == null) {
            destinations = new ArrayList<Destinations>();
        }
        destinations.add(destination);
    }
    
    
    
}
