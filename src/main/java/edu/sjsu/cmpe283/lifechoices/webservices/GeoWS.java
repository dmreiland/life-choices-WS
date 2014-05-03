package edu.sjsu.cmpe283.lifechoices.webservices;

import edu.sjsu.cmpe283.lifechoices.webservices.dto.GeoHistoryDTO;
import edu.sjsu.cmpe283.lifechoices.entities.UserGeoHistory;
import edu.sjsu.cmpe283.lifechoices.services.UserGeoHistoryService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * User: maksim
 * Date: 3/1/14 - 11:50 AM
 */
@Controller
@RequestMapping("/v1/geo")
public class GeoWS {

    private static Log logger = LogFactory.getLog(GeoWS.class);
    private static long defaultTimeDiff = 3600000; // 1 hr in milliseconds

    @Autowired
    UserGeoHistoryService userGeoHistoryService;

    @SuppressWarnings("rawtypes")
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

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/plain")
    public ResponseEntity availableMethods() {

        String txt =
                "New geo location\n" +
                "----------------------\n" +
                "\n" +
                "URI: **/v1/geo/**\n" +
                "Method: **POST**\n" +
                "Consume type: **application/json**\n" +
                "Request Payload:\n" +
                "\n" +
                "{\n" +
                "    \"userName\":\"maksim\",\n" +
                "    \"timestamp\":1394932551,\n" +
                "    \"latitude\" : 37.415365 ,\n" +
                "    \"longitude\" : -122.089803\n" +
                " }\n" +
                "Response type: **application/json**\n" +
                "Response Payload Sample:\n" +
                "\n" +
                "{\n" +
                "    \"id\":\"5324fb6803647201bbbfe4da\",\n" +
                "    \"timestamp\":1.39493261E9,\n" +
                "    \"latitude\":37.415363,\n" +
                "    \"longitude\":-122.089806,\n" +
                "    \"userName\":\"maksim\"\n" +
                "}\n" +
                "Response Status: **201 Created**\n" +
                "\n" +
                "User Location History\n" +
                "---------------------\n" +
                "URI: **/v1/geo/history/{username}?starttime=123&endtime=234** *(starttime & endtime are optional. Default value will be a time BETWEEN now and 1 hr ago)*\n" +
                "Method: **GET**\n" +
                "Consume type: N/A\n" +
                "Request Payload: N/A\n" +
                "Response type: **application/json**\n" +
                "Response Payload Sample:\n" +
                "\n" +
                "Example URL: **/v1/geo/history/maksim?starttime=1394939000&endtime=1394939999**\n" +
                "\n" +
                "{\n" +
                "    \"endtime\":1394939000,\n" +
                "    \"username\":\"maksim\",\n" +
                "    \"starttime\":1394939999,\n" +
                "    \"history\":[\n" +
                "                {\n" +
                "                    \"id\":\"5325267e0364bc3dfe32da27\",\n" +
                "                    \"timestamp\":1394939032,\n" +
                "                    \"position\":[37.41536331176758,-122.08980560302734],\n" +
                "                    \"userName\":\"maksim\"\n" +
                "                }\n" +
                "              ]\n" +
                "}\n" +
                "\n";


        return new ResponseEntity<String>(txt, HttpStatus.OK);
    }
}
