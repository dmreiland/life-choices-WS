package edu.sjsu.cmpe283.lifechoices.webservices;

import edu.sjsu.cmpe283.lifechoices.entities.UserSearchHistory;
import edu.sjsu.cmpe283.lifechoices.services.ATTSpeechToTextService;
import edu.sjsu.cmpe283.lifechoices.services.UserSearchHistoryService;
import edu.sjsu.cmpe283.lifechoices.services.WolframAlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * User: maksim
 * Date: 5/3/14 - 6:21 PM
 */
@RestController
@RequestMapping("/v1/search")
public class WolframWS {

    WolframAlphaService wolframAlphaService = new WolframAlphaService();
    ATTSpeechToTextService attSpeechToTextService = new ATTSpeechToTextService();

    @Autowired
    UserSearchHistoryService userSearchHistoryService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getSearch(@RequestParam(value = "q", required = true) String queryString,
                                    @RequestParam(value = "raw", required = false, defaultValue = "false") Boolean isRaw,
                                    @RequestParam(value = "uid", required = false) String userId) {

        String jsonResult = "";

        if (isRaw) {
            jsonResult = wolframAlphaService.rawJson(queryString);
        } else {
            jsonResult = wolframAlphaService.formattedJson(queryString);
        }

        UserSearchHistory userSearchHistory = new UserSearchHistory();
        userSearchHistory.setUserName(userId);
        userSearchHistory.setSearchQuery(queryString);
        userSearchHistory.setTimestamp(new Date());
        userSearchHistoryService.save(userSearchHistory);

        return new ResponseEntity<String>(jsonResult, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity handleFileUpload(@RequestParam(value = "q-voice", required = true) MultipartFile file,
                                           @RequestParam(value = "raw", required = false, defaultValue = "false") Boolean isRaw,
                                           @RequestParam(value = "uid", required = false) String userId) {

        String name = "/tmp/voice-search-upload-" + new Date().getTime() + "-" + file.getOriginalFilename();
        File f = new File(name);


        if (!file.isEmpty()) {
            try {

                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(f));
                stream.write(bytes);
                stream.close();

                String searchQuery = attSpeechToTextService.getText(f);

                ResponseEntity response = getSearch(searchQuery, isRaw, userId);

                f.delete();

                return response;
            } catch (Exception e) {
                return new ResponseEntity<String>("You failed to upload [" + f.getAbsolutePath() + "] => " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<String>("You failed to upload " + name + " because the file was empty.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
