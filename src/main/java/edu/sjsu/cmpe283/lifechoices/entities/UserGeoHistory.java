package edu.sjsu.cmpe283.lifechoices.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User's Geo Location history
 * User: maksim
 * Date: 3/9/14 - 9:50 PM
 */
@Data
@Document
public class UserGeoHistory {

    @Id
    private String id;

    /**
     * Time stamp in milliseconds
     */
    private long timestamp;

    /**
     * Position (  [x,y] where x is latitude, and y is longitude )
     */
    private double[] position;

    /**
     * User to whom this location belong
     */
    private String userName;

    /**
     * Mark this history as Check In history
     */
    private UserGeoHistoryType historyType;

    private String yelpId;

    public UserGeoHistory(){

    }

    /**
     * @param timestamp Time Stamp
     * @param position  Location point
     * @param userName
     */
    public UserGeoHistory(long timestamp, double[] position, String userName) {
        this.timestamp = timestamp;
        this.position = position;
        this.userName = userName;
    }

    /**
     * @param timestamp Time Stamp
     * @param position  Location point
     * @param userName
     */
    public UserGeoHistory(long timestamp, double[] position, String userName, UserGeoHistoryType historyType, String yelpId) {
        this.timestamp = timestamp;
        this.position = position;
        this.userName = userName;
        this.historyType = historyType;
        this.yelpId = yelpId;
    }

    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    
    public long getTimestamp() {
        return timestamp;
    }

    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    
    public double[] getPosition() {
        return position;
    }

    
    public void setPosition(double[] position) {
        this.position = position;
    }

    
    public String getUserName() {
        return userName;
    }

    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    public UserGeoHistoryType getHistoryType() {
        return historyType;
    }

    
    public void setHistoryType(UserGeoHistoryType historyType) {
        this.historyType = historyType;
    }

    
    public String getYelpId() {
        return yelpId;
    }

    
    public void setYelpId(String yelpId) {
        this.yelpId = yelpId;
    }
    
    

}
