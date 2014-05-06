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

import edu.sjsu.cmpe283.lifechoices.services.MeetupService;

@RestController
@RequestMapping("/v2/events")
public class MeetupWS {
    
    private static Log logger = LogFactory.getLog(MeetupWS.class);
    
    @Autowired
    MeetupService meetingService;
    
    
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity updateUser(
            @RequestParam(value = "latitude", defaultValue = "37.334679", required = false) Double latitude,
            @RequestParam(value = "longitude", defaultValue = "-121.881113", required = false) Double longitude,
            @RequestParam(value = "category",  required = false) String category,
            @RequestParam(value = "mode", defaultValue = "html",  required = false) String mode,
            @RequestParam(value = "from", defaultValue = "0d", required = false) String from,
            @RequestParam(value = "to", defaultValue = "1m", required = false) String to,
            @RequestParam(value = "page", defaultValue = "20", required = false) Integer page,
            @RequestParam(value = "radius", required = false) Integer radius) {

        try {
          return new ResponseEntity<String>(meetingService.getEvents(latitude, longitude, category, from, to, mode, page, radius), HttpStatus.OK);
      }
      catch (Exception e) {
          logger.error(e);
          return new ResponseEntity<String>("ERR: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getCategories() {

        try {
          return new ResponseEntity<String>(meetingService.getCategories(), HttpStatus.OK);
      }
      catch (Exception e) {
          logger.error(e);
          return new ResponseEntity<String>("ERR: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
        
    }
}
