package edu.sjsu.cmpe283.lifechoices.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private long timestamp;

    /**
     * User to whom this location belong
     */
    private String userName;

    private String searchQuery;
}
