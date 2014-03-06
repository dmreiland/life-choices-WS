package edu.sjsu.cmpe283.lifechoices.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * User: maksim
 * Date: 3/5/14 - 6:13 PM
 */
@Data
@Entity
public class User{

    @Id
    private Long id;
    private String name;
}
