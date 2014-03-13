package edu.sjsu.cmpe283.lifechoices.entities;

import lombok.Data;

import org.springframework.data.annotation.Id;

/**
 * User's Geo Location history
 * User: maksim
 * Date: 3/9/14 - 9:50 PM
 */
@Data
public class UserGeoHistory {

    @Id
    private String id;

    /**
     * Time stamp in milliseconds
     */
    private long time;

    /**
     * Geo location - latitude
     */
    private long latitude;

    /**
     * Geo location - longitude
     */
    private long longitude;

    /**
     * User to whom this location belong
     */
    private User user;

    /**
     *
     * @param time
     * @param latitude
     * @param longitude
     */
    public UserGeoHistory(long time, long latitude, long longitude, User user){
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
    }

}
