package edu.sjsu.cmpe283.lifechoices.webservices;

import edu.sjsu.cmpe283.lifechoices.dto.GeoHistoryDTO;
import edu.sjsu.cmpe283.lifechoices.entities.UserGeoHistory;
import edu.sjsu.cmpe283.lifechoices.services.UserGeoHistoryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * User: maksim
 * Date: 3/1/14 - 11:50 AM
 */
@RestController
@RequestMapping("/v1/geo")
public class GeoWS {

    private static Log logger = LogFactory.getLog(GeoWS.class);
    private static long defaultTimeDiff = 3600000; // 1 hr in milliseconds

    @Autowired
    UserGeoHistoryService userGeoHistoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<HashMap<String, Object>> availableMethods() {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("message", "hello from the server");
        hashMap.put("status", "ok");


        return new ResponseEntity<HashMap<String, Object>>(hashMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity postNewGeoHistory(@RequestBody(required = true) @Valid GeoHistoryDTO geoHistoryDTO) {

        String username = geoHistoryDTO.getUserName();
        double lat = geoHistoryDTO.getLatitude();
        double lon = geoHistoryDTO.getLongitude();
        long timestamp = geoHistoryDTO.getTimestamp();

        double[] position = {lat, lon};

        UserGeoHistory u =  userGeoHistoryService.save(new UserGeoHistory(timestamp, position, username));

        return new ResponseEntity<UserGeoHistory>(u, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/history/{username}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<HashMap<String, Object>> userGeoHistory(
            @PathVariable("username") String username,
            @RequestParam(value = "starttime", required = false) Long starttime,
            @RequestParam(value = "endtime", required = false) Long endtime

    ) {

        logger.info("username=["+ username + "], starttime=[" + starttime +"], endtime=[" + endtime + "]");

        if(starttime == null || starttime == null){
            endtime = new Date().getTime();
            starttime = endtime - defaultTimeDiff;

            logger.info("Changed time to starttime=[" + starttime +"], endtime=[" + endtime + "]");
        }

        // Adjust by 1 millisecond to make sure we get equal times
        endtime++;
        starttime--;

        List<UserGeoHistory> historyList = userGeoHistoryService.findByTimestampBetweenAndUsername(starttime, endtime, username);

        logger.info("History list size =[" + historyList.size() + "]");


        HashMap<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put("username", username);
        responseMap.put("starttime", starttime);
        responseMap.put("endtime", endtime);
        responseMap.put("history", historyList);

        logger.info("Finish '/history/" + username + "' call");

        return new ResponseEntity<HashMap<String, Object>>(responseMap, HttpStatus.OK);
    }


}
