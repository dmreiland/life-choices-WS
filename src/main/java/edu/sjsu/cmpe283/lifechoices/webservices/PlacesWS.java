package edu.sjsu.cmpe283.lifechoices.webservices;

import com.googlecode.placesapiclient.client.entity.Place;
import edu.sjsu.cmpe283.lifechoices.services.GooglePlacesService;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.PlacesType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: maksim
 * Date: 3/16/14 - 10:54 AM
 */
@RestController
@RequestMapping("/v1/places")
public class PlacesWS {

    private static Log logger = LogFactory.getLog(PlacesWS.class);

    @Autowired
    GooglePlacesService googlePlacesService;

    @RequestMapping(value = "/{latitude}/{longitude}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity userGeoHistory(
            @PathVariable("latitude") double latitude,
            @PathVariable("longitude") double longitude,

            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "radiusinmeters", defaultValue = "500", required = false) Integer radiusinmeters

    ) {

        logger.info("latitude=[" + latitude + "], longitude=[" + longitude + "], type=[" + type + "], radiusinmeters=[" + radiusinmeters + "]");


        if (type == null) {
            type = PlacesType.allInPipe();
        }

        List<Place> googlePlaces = googlePlacesService.getGooglePlaces(type, latitude, longitude, radiusinmeters);

        return new ResponseEntity<List<Place>>(googlePlaces, HttpStatus.OK);
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getGooglePlacesTypes() {
        PlacesType[] placesTypes = PlacesType.values();

        return new ResponseEntity<PlacesType[]>(placesTypes, HttpStatus.OK);
    }
}
