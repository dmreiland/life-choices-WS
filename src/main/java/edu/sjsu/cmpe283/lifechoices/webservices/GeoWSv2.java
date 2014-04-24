package edu.sjsu.cmpe283.lifechoices.webservices;

import edu.sjsu.cmpe283.lifechoices.entities.UserGeoHistory;
import edu.sjsu.cmpe283.lifechoices.entities.UserGeoHistoryType;
import edu.sjsu.cmpe283.lifechoices.services.UserGeoHistoryService;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.GeoHistoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * User: maksim
 * Date: 3/1/14 - 11:50 AM
 */
@Controller
@RequestMapping("/v2/geo")
public class GeoWSv2 {

    @Autowired
    UserGeoHistoryService userGeoHistoryService;

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity postNewGeoHistory(@RequestBody(required = true) @Valid GeoHistoryDTO[] geoHistoryDTO) {

        List<UserGeoHistory> histories = new ArrayList<UserGeoHistory>();
        for(GeoHistoryDTO g : geoHistoryDTO) {
            String username = g.getUserName();
            double lat = g.getLatitude();
            double lon = g.getLongitude();
            long timestamp = g.getTimestamp();

            double[] position = {lat, lon};

            UserGeoHistoryType geoHistoryType = g.getGeoHistoryType();

            UserGeoHistory ugh = new UserGeoHistory(timestamp, position, username, geoHistoryType);

            histories.add(ugh);
        }

        List<UserGeoHistory> historyList =  userGeoHistoryService.save(histories);

        return new ResponseEntity<List>(historyList, HttpStatus.CREATED);
    }

}
