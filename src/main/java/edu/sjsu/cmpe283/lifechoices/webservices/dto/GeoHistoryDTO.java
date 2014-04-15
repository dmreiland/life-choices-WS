package edu.sjsu.cmpe283.lifechoices.webservices.dto;

import lombok.Data;

/**
 * User: maksim
 * Date: 3/12/14 - 5:30 PM
 */
@Data
public class GeoHistoryDTO {

    private String userName;
    private float latitude;
    private float longitude;
    private long timestamp;
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public float getLatitude() {
        return latitude;
    }
    
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
    
    public float getLongitude() {
        return longitude;
    }
    
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    
    
    
}
