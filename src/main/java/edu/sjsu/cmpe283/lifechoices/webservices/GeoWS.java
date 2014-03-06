package edu.sjsu.cmpe283.lifechoices.webservices;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * User: maksim
 * Date: 3/1/14 - 11:50 AM
 */
@RestController
@RequestMapping("/v1/geo")
public class GeoWS {
    @RequestMapping(value = "/", method= RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    HashMap<String, Object> availableMethods(){

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("message", "hello from the server");
        hashMap.put("status", "ok");


        return hashMap;
    }
}
