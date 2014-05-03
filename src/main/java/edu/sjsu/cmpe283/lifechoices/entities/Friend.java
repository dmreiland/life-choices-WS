package edu.sjsu.cmpe283.lifechoices.entities;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User: maksim
 * Date: 4/12/14 - 1:35 PM
 */
@Data
@Document
public class Friend {
    @Id
    private String id;

    /**
     * Name in a format First name + Last name.
     * Ex. "John Smith"
     */
    String name;

    /**
     * Social network unique ID
     */
    String socialNetworkUID;

    
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getSocialNetworkUID() {
        return socialNetworkUID;
    }

    
    public void setSocialNetworkUID(String socialNetworkUID) {
        this.socialNetworkUID = socialNetworkUID;
    }
}
