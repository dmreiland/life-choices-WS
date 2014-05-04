package edu.sjsu.cmpe283.lifechoices.webservices;

import edu.sjsu.cmpe283.lifechoices.services.WolframAlphaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: maksim
 * Date: 5/3/14 - 6:21 PM
 */
@RestController
@RequestMapping("/v1/search")
public class WolframWS {

    WolframAlphaService wolframAlphaService = new WolframAlphaService();

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getSearch(@RequestParam(value = "q", required = true) String queryString,
                                    @RequestParam(value = "raw", required = false, defaultValue = "false") Boolean isRaw){

        String jsonResult = "";

        if(isRaw){
            jsonResult = wolframAlphaService.rawJson(queryString);
        } else {
            jsonResult = wolframAlphaService.formattedJson(queryString);
        }

        return new ResponseEntity<String>(jsonResult, HttpStatus.OK);
    }


}
