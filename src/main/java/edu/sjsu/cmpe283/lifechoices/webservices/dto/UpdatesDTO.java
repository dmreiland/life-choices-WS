package edu.sjsu.cmpe283.lifechoices.webservices.dto;

import edu.sjsu.cmpe283.lifechoices.services.GoogleMapsService;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.domain.GoogleMapsDirections;
import lombok.Data;

public class UpdatesDTO {
    
    Double originLatitude;
    Double originLongitude;
    Double destinationLatitude;
    Double destinationLongitude;
    
    String googleMapsStaticLink;
    String googleMapsDistanceToDestination;
    String googleMapsTimeToDestination;
    
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
