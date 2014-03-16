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
     * Position (  [x(lat),y(lon)]  )
     */
    private double[] position;

    /**
     * User to whom this location belong
     */
    private String userName;

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

}
