package edu.sjsu.cmpe283.lifechoices.webservices;

import edu.sjsu.cmpe283.lifechoices.dto.GeoHistoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * User: maksim
 * Date: 3/1/14 - 11:50 AM
 */
@RestController
@RequestMapping("/v1/geo")
public class GeoWS {
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<HashMap<String, Object>> availableMethods() {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("message", "hello from the server");
        hashMap.put("status", "ok");


        return new ResponseEntity<HashMap<String, Object>>(hashMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Map<String, Object>> postNewGeoHistory(@RequestBody(required = true) GeoHistoryDTO geoHistoryDTO) {


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }


        long lat = geoHistoryDTO.getLatitude();
        long lon = geoHistoryDTO.getLongitude();




        Map<String, Object> response = new HashMap<String, Object>();
        response.put("username", username);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }



}
