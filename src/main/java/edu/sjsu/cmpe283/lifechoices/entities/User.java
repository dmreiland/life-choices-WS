package edu.sjsu.cmpe283.lifechoices.entities;

import lombok.Data;

import org.springframework.data.annotation.Id;



/**
 * User: maksim
 * Date: 3/5/14 - 6:13 PM
 */
@Data
public class User{

    @Id
    private String id;
    private String name;
}
