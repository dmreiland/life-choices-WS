package edu.sjsu.cmpe283.lifechoices.webservices;

import com.google.common.collect.HashMultimap;
import edu.sjsu.cmpe283.lifechoices.entities.UserGeoHistory;
import edu.sjsu.cmpe283.lifechoices.entities.UserGeoHistoryType;
import edu.sjsu.cmpe283.lifechoices.services.UserGeoHistoryService;
import edu.sjsu.cmpe283.lifechoices.services.YelpService;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.GeoHistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: maksim
 * Date: 3/1/14 - 11:50 AM
 */
@Controller
@RequestMapping("/v2/geo")
public class GeoWSv2 {

    @Autowired
    UserGeoHistoryService userGeoHistoryService;

    @Autowired
    YelpService yelpService;
    
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity postNewGeoHistory(@RequestBody(required = true) @Valid GeoHistoryDTO[] geoHistoryDTO) {

        List<UserGeoHistory> histories = new ArrayList<UserGeoHistory>();
        for (GeoHistoryDTO g : geoHistoryDTO) {
            String username = g.getUserName();
            double lat = g.getLatitude();
            double lon = g.getLongitude();
            long timestamp = g.getTimestamp();

            double[] position = {lat, lon};

            String yelpId = g.getYelpId();

            UserGeoHistoryType geoHistoryType = g.getGeoHistoryType();

            UserGeoHistory ugh = new UserGeoHistory(timestamp, position, username, geoHistoryType, yelpId);

            histories.add(ugh);
        }

        List<UserGeoHistory> historyList = userGeoHistoryService.save(histories);

        return new ResponseEntity<List>(historyList, HttpStatus.CREATED);
    }


    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/friends-around", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public ResponseEntity sadf(
            @RequestParam(value = "latitude", required = true) Double x,
            @RequestParam(value = "longitude", required = true) Double y,
            @RequestParam(value = "radius-in-meters", defaultValue = "500", required = false) double radiusinmeters,
            @RequestParam(value = "start-time", required = true) long startTime,
            @RequestParam(value = "end-time", required = true) long endTime,
            @RequestParam(value = "friends", required = true) String[] friendsIds
    ) {

        Point centerPoint = new Point(x, y);
        Circle circle = new Circle(centerPoint, radiusinmeters);
        Map<String, Object> byPositionWithinAndTimestampBetweenAndUserName = userGeoHistoryService.findByPositionWithinAndTimestampBetweenAndUserName(circle, startTime, endTime, friendsIds);


        return new ResponseEntity<Map>(byPositionWithinAndTimestampBetweenAndUserName, HttpStatus.FOUND);
    }
    
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/typed-location", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity asdf(@RequestParam(value = "uid", required = true) String userId,
                               @RequestParam(value = "type", required = false) UserGeoHistoryType type) {

        List<UserGeoHistory> typedLocationHistorie = userGeoHistoryService.findByUserNameAndHistoryType(userId, type);


        return new ResponseEntity<List>(typedLocationHistorie, HttpStatus.OK);

    }

    @RequestMapping(value = "/most-visited-place", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getUsersMostCheckedInPlaces(@RequestParam(value = "uid", required = true) String userId) {

        List<UserGeoHistory> checkedInYelpPlaces = userGeoHistoryService.findByUserNameAndHistoryType(userId, UserGeoHistoryType.CHECKIN);

        HashMultimap<String, UserGeoHistory> yelpIdMap = HashMultimap.create();


        // put all yelp ids and associate them with the history object
        for (UserGeoHistory c : checkedInYelpPlaces) {
            yelpIdMap.put(c.getYelpId(), c);
        }


        /**
         * Let's search for the most visited place
         */
        String largestYelpId = "";
        int largest = 0;

        for (String yelpId : yelpIdMap.keySet()) {
            int size = yelpIdMap.get(yelpId).size();
            System.out.println("Key: " + yelpId + " size: " + size);

            if (largest < size) {
                largest = size;
                largestYelpId = yelpId;
            }
        }

        System.out.println("ID " + largestYelpId + " was the largest (" + largest + ")");

        return new ResponseEntity<String>(yelpService.getYelpById(largestYelpId), HttpStatus.OK);

    }


}
