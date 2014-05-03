package edu.sjsu.cmpe283.lifechoices.webservices;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe283.lifechoices.services.UpdatesService;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.UpdatesDTOV2;


@RestController
@RequestMapping("/v2/updates")
public class UpdatesWSv2 {
    
    private static Log logger = LogFactory.getLog(UpdatesWSv2.class);

    @Autowired
    UpdatesService updatesService;

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity updates(
            @RequestParam(value = "latitude", defaultValue = "37.334679", required = false) Double latitude,
            @RequestParam(value = "longitude", defaultValue = "-121.881113", required = false) Double longitude,
            @RequestParam(value = "width", defaultValue = "640", required = false) Integer width,
            @RequestParam(value = "height", defaultValue = "480", required = false) Integer height,
            @RequestParam(value = "units", defaultValue = "imperial", required = false) String units,
            @RequestParam(value = "forecast", defaultValue = "3", required = false) Integer forecastCount,
            @RequestParam(value = "maps", defaultValue = "true", required = false) Boolean getMaps
    ) {
        try {
//            Sample Data:
//            SJSU:             37.334679,-121.881113
//            Moffett Field:    37.412985, -122.053472
//            Spartan Stadium:  37.3212995, -121.8696786 
//            SFO Intl :        37.6191050, -122.3752372 
//            SJ Mineta Intl:   37.3653473, -121.9157925 
//            Great America:    37.390052, -121.9781685 
//            
            return new ResponseEntity<UpdatesDTOV2>(updatesService.getUpdatesV2(getMaps, latitude, longitude, units, forecastCount, width, height) , HttpStatus.OK);
        }
        catch (Exception e) {
            logger.error(e);
            return new ResponseEntity<String>("ERR: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        
    }
    
    
}
