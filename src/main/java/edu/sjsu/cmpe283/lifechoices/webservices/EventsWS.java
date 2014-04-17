package edu.sjsu.cmpe283.lifechoices.webservices;

import edu.sjsu.cmpe283.lifechoices.services.YelpService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: srkarra
 */
@RestController
@RequestMapping("/v1/events")
public class EventsWS {
    private static Log logger = LogFactory.getLog(EventsWS.class);

    @Autowired
    YelpService yelpService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getEvents(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam(value = "keyword", defaultValue = "events", required = false) String searchTerm,
            @RequestParam(value = "radius", required = false) Integer radius,
            @RequestParam(value = "deals", required = false) String hasDeals

    ) {
        logger.info("Events: latitude=[" + latitude + "], longitude=[" + longitude + "], keyword=[" + searchTerm + "], radius=[" + radius + "], hasDeals=[" + hasDeals + "]");
        String response = yelpService.getPlaces(searchTerm, latitude, longitude, radius, hasDeals);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
