package edu.sjsu.cmpe283.lifechoices.webservices;

import edu.sjsu.cmpe283.lifechoices.entities.UserGeoHistory;
import edu.sjsu.cmpe283.lifechoices.entities.UserGeoHistoryType;
import edu.sjsu.cmpe283.lifechoices.services.UserGeoHistoryService;
import edu.sjsu.cmpe283.lifechoices.services.UserService;
import edu.sjsu.cmpe283.lifechoices.services.YelpService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

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

    @Autowired
    UserGeoHistoryService userGeoHistoryService;

    @Autowired
    UserService userService;


    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity userFoods(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam(value = "keyword", required = false) String searchTerm,
            @RequestParam(value = "radius", required = false) Integer radius,
            @RequestParam(value = "deals", required = false) String hasDeals

    ) {
        logger.info("latitude=[" + latitude + "], longitude=[" + longitude + "], keyword=[" + searchTerm + "], radius=[" + radius + "], hasDeals=[" + hasDeals + "]");
        String response = yelpService.getYelpResponseJson(searchTerm, latitude, longitude, radius, hasDeals);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/user-details", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity userFoodsWithCheckedInPlaces(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "keyword", required = false) String searchTerm,
            @RequestParam(value = "radius", required = false) Integer radius,
            @RequestParam(value = "deals", required = false) String hasDeals

    ) throws ParseException {
        logger.info("latitude=[" + latitude + "], longitude=[" + longitude + "], keyword=[" + searchTerm + "], radius=[" + radius + "], hasDeals=[" + hasDeals + "]");

        String responseJson = yelpService.getYelpResponseJson(searchTerm, latitude, longitude, radius, hasDeals);


        JSONParser parser = new JSONParser();
        Object obj = parser.parse(responseJson);
        JSONObject jsonObject = (JSONObject) obj;
        jsonObject.put("user-id", userId);
        jsonObject.put("user-info", userService.find(userId));

        JSONArray businesses = (JSONArray) jsonObject.get("businesses");

        Iterator<JSONObject> iterator = businesses.iterator();
        while (iterator.hasNext()) {
            JSONObject businessJsonObj = iterator.next();

            String yelpBusinessId = (String) businessJsonObj.get("id");
            System.out.println(yelpBusinessId);


            List<UserGeoHistory> checkedInYelpPlaces = userGeoHistoryService.findByUserNameAndYelpIdAndHistoryType(userId, yelpBusinessId, UserGeoHistoryType.CHECKIN);

            businessJsonObj.put("num-of-visits", checkedInYelpPlaces.size());
            businessJsonObj.put("has-been-visited", checkedInYelpPlaces.size() > 0);
        }


        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
    }
}
