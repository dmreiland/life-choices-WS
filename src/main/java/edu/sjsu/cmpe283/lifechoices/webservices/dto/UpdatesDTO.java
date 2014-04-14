package edu.sjsu.cmpe283.lifechoices.webservices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.sjsu.cmpe283.lifechoices.webservices.dto.domain.GoogleMapsDirections;
import lombok.Data;

public class UpdatesDTO {
    @JsonProperty("origin_lat")
    Double originLatitude;
    @JsonProperty("origin_long")
    Double originLongitude;
    @JsonProperty("destination_lat")
    Double destinationLatitude;
    @JsonProperty("destination_long")
    Double destinationLongitude;
    
    @JsonProperty("maps_url")
    String googleMapsStaticLink;
    
    @JsonProperty("distance")
    String googleMapsDistanceToDestination;
    
    @JsonProperty("eta")
    String googleMapsTimeToDestination;
    
    
    @JsonProperty("raw_directions")
    GoogleMapsDirections rawDirections;

    
    public Double getOriginLatitude() {
        return originLatitude;
    }

    
    public void setOriginLatitude(Double originLatitude) {
        this.originLatitude = originLatitude;
    }

    
    public Double getOriginLongitude() {
        return originLongitude;
    }

    
    public void setOriginLongitude(Double originLongitude) {
        this.originLongitude = originLongitude;
    }

    
    public Double getDestinationLatitude() {
        return destinationLatitude;
    }

    
    public void setDestinationLatitude(Double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    
    public Double getDestinationLongitude() {
        return destinationLongitude;
    }

    
    public void setDestinationLongitude(Double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
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

    
    public void setGoogleMapsDistanceToDestination(String googleMapsDistanceToDestination) {
        this.googleMapsDistanceToDestination = googleMapsDistanceToDestination;
    }

    
    public String getGoogleMapsTimeToDestination() {
        return googleMapsTimeToDestination;
    }

    
    public void setGoogleMapsTimeToDestination(String googleMapsTimeToDestination) {
        this.googleMapsTimeToDestination = googleMapsTimeToDestination;
    }

    
    public GoogleMapsDirections getRawDirections() {
        return rawDirections;
    }

    
    public void setRawDirections(GoogleMapsDirections rawDirections) {
        this.rawDirections = rawDirections;
    }

    
    
    
    
    
    
    
}
