package edu.sjsu.cmpe283.lifechoices.dto;

import lombok.Data;

/**
 * User: maksim
 * Date: 3/12/14 - 5:30 PM
 */
@Data
public class GeoHistoryDTO {

    private Long userId;
    private long latitude;
    private long longitude;
    private long time;

}
