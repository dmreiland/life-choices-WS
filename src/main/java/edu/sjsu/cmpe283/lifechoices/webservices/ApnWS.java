package edu.sjsu.cmpe283.lifechoices.webservices;

import edu.sjsu.cmpe283.lifechoices.utils.APNService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Apple Push Notification Web Service
 * User: maksim
 * Date: 4/14/14 - 8:07 PM
 */
@RestController
@RequestMapping("/v1/apn")
public class ApnWS {

    private static Log logger = LogFactory.getLog(FoodWS.class);

    @Value("${apn.token-to-robs-iphone}")
    String tokenToRobsIphone;
    
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity sendAPNToRob(
            @RequestParam(value = "message", required = false) String message,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "badge", required = false) Integer badge,
            @RequestParam(value = "send", required = false) boolean send
            ){

        Map<String, Object> responseMap = new HashMap<String, Object>();

        if(message == null || message.isEmpty()) {
            message = new StringBuilder().append("Heavy traffic on CA 101 N. See map...").append("[").append(new Date().toString()).append("]").toString();
        }

        if(token == null) {
            token = tokenToRobsIphone;
        }

        if(badge == null) {
            badge = 1;
        }



        if(send) {
            APNService.send(token, message, badge);
        }


        responseMap.put("message", message);
        responseMap.put("token", token);
        responseMap.put("badge", badge);

        responseMap.put("time", new Date().toString());

        return new ResponseEntity<Map>(responseMap, HttpStatus.OK);
    }
}
