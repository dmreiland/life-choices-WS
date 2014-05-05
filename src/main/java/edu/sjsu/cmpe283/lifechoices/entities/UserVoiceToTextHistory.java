package edu.sjsu.cmpe283.lifechoices.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * User: maksim
 * Date: 5/5/14 - 8:44 AM
 */
@Data
@Document
public class UserVoiceToTextHistory {

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

    private String transcribedText;
}
