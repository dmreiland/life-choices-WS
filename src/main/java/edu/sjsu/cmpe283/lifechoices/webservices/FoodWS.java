package edu.sjsu.cmpe283.lifechoices.webservices;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe283.lifechoices.services.YelpService;

/**
 * User: srkarra
 * Date: 3/16/14 - 4:00 PM
 */
@RestController
@RequestMapping("/v1/food")
public class FoodWS {
    private static Log logger = LogFactory.getLog(FoodWS.class);

    @Autowired
    YelpService yelpService;

    @RequestMapping(value = "/{latitude}/{longitude}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity userGeoHistory(
            @PathVariable("latitude") double latitude,
            @PathVariable("longitude") double longitude,
            @RequestParam(value = "keyword", required = false) String searchTerm

    ) {
        logger.info("latitude=[" + latitude + "], longitude=[" + longitude + "], keyword=[" + searchTerm + "]");
        String response = yelpService.getFoodPlaces(searchTerm, latitude, longitude);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
