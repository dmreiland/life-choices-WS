package edu.sjsu.cmpe283.lifechoices.webservices.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.sjsu.cmpe283.lifechoices.webservices.dto.domain.GoogleMapsDirections;

public class UpdatesDTO {
    @JsonProperty("latitude")
    Double latitude;
    @JsonProperty("longitude")
    Double longitude;
    @JsonProperty("weather")
    Weather weather;
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

    
    public Weather getWeather() {
        return weather;
    }

    
    public void setWeather(Weather weather) {
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static class Destinations {
        @JsonProperty("latitude")
        Double latitude;
        @JsonProperty("longitude")
        Double longitude;
        
        @JsonProperty("weather")
        Weather weather;
        
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

        
        public Weather getWeather() {
            return weather;
        }

        
        public void setWeather(Weather weather) {
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
    
    
    
    
    
    
    
    
    
    
    public static class Weather {
        
        
        
    }
    
    
    
    
    
    
}
