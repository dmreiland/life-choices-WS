package edu.sjsu.cmpe283.lifechoices.webservices.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.sjsu.cmpe283.lifechoices.webservices.dto.domain.GoogleMapsDirections;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.domain.WeatherV2;

public class UpdatesDTOV2 {
    @JsonProperty("latitude")
    Double latitude;
    @JsonProperty("longitude")
    Double longitude;
    @JsonProperty("weather")
    WeatherV2 weather;
    @JsonProperty("destinations")
    List<DestinationsV2> destinations;
    
    
    
    
    
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

    
    public List<DestinationsV2> getDestinations() {
        return destinations;
    }

    
    public void setDestinations(List<DestinationsV2> destinations) {
        this.destinations = destinations;
    }

    
    public void addDesination(DestinationsV2 destination) {
        if(destinations == null) {
            destinations = new ArrayList<DestinationsV2>();
        }
        destinations.add(destination);
    }
    
    
    
    
    public static class DestinationsV2 {
        @JsonProperty("latitude")
        Double latitude;
        @JsonProperty("longitude")
        Double longitude;
        
        @JsonProperty("weather")
        WeatherV2 weather;
        
        @JsonProperty("maps_url")
        String googleMapsStaticLink;
        
        @JsonProperty("distance")
        String googleMapsDistanceToDestination;
        
        @JsonProperty("eta")
        String googleMapsTimeToDestination;
        
        @JsonProperty("raw_directions")
        GoogleMapsDirections rawDirections;

        
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

        
        public String getGoogleMapsStaticLink() {
            return googleMapsStaticLink;
        }

        
        public void setGoogleMapsStaticLink(String googleMapsStaticLink) {
            this.googleMapsStaticLink = googleMapsStaticLink;
        }

        
        public String getGoogleMapsDistanceToDestination() {
            return googleMapsDistanceToDestination;
        }

        
        public void setDistanceToDestination(String googleMapsDistanceToDestination) {
            this.googleMapsDistanceToDestination = googleMapsDistanceToDestination;
        }

        
        public String getGoogleMapsTimeToDestination() {
            return googleMapsTimeToDestination;
        }

        
        public void setTimeToDestination(String googleMapsTimeToDestination) {
            this.googleMapsTimeToDestination = googleMapsTimeToDestination;
        }

        
        public GoogleMapsDirections getRawDirections() {
            return rawDirections;
        }

        
        public void setRawDirections(GoogleMapsDirections rawDirections) {
            this.rawDirections = rawDirections;
        }
    }
    
}
