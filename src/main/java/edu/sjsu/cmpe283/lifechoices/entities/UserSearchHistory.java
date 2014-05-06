package edu.sjsu.cmpe283.lifechoices.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * User: maksim
 * Date: 5/4/14 - 10:31 AM
 */
@Data
@Document
public class UserSearchHistory {

    @Id
    private String id;

    /**
     * Time stamp in milliseconds
     */
    @CreatedDate
    private Date timestamp;

    /**
     * User to whom this location belong
     */
    private String userName;

    private String searchQuery;

    
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    
    public Date getTimestamp() {
        return timestamp;
    }

    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    
    public String getUserName() {
        return userName;
    }

    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    public String getSearchQuery() {
        return searchQuery;
    }

    
    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
    
}
