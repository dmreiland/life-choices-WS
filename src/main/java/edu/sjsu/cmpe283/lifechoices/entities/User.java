package edu.sjsu.cmpe283.lifechoices.entities;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * User: maksim
 * Date: 3/5/14 - 6:13 PM
 */
@Data
@Document
public class User{

    @Id
    private String username;
    private String name;
}
