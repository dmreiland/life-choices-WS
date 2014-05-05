package edu.sjsu.cmpe283.lifechoices.webservices;

import edu.sjsu.cmpe283.lifechoices.entities.UserVoiceToTextHistory;
import edu.sjsu.cmpe283.lifechoices.repositories.UserVoiceToTextHistoryRepository;
import edu.sjsu.cmpe283.lifechoices.services.ATTSpeechToTextService;
import edu.sjsu.cmpe283.lifechoices.services.UserSearchHistoryService;
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
 * Date: 5/5/14 - 8:27 AM
 */
@RestController
@RequestMapping("/v1/voice")
public class VoiceWS {

    ATTSpeechToTextService attSpeechToTextService = new ATTSpeechToTextService();

    @Autowired
    UserVoiceToTextHistoryRepository userVoiceToTextHistoryRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity handleFileUpload(@RequestParam(value = "q-voice", required = true) MultipartFile file,
                                           @RequestParam(value = "uid", required = false) String userId) {

        String name = "/tmp/voice-search-upload-" + new Date().getTime() + "-" + file.getOriginalFilename();
        File f = new File(name);


        if (!file.isEmpty()) {
            try {

                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(f));
                stream.write(bytes);
                stream.close();

                String transcribedText = attSpeechToTextService.getText(f);

                UserVoiceToTextHistory userVoiceToTextHistory = new UserVoiceToTextHistory();
                userVoiceToTextHistory.setTimestamp(new Date());
                userVoiceToTextHistory.setUserName(userId);
                userVoiceToTextHistory.setTranscribedText(transcribedText);
                userVoiceToTextHistoryRepository.save(userVoiceToTextHistory);

                f.delete();

                return new ResponseEntity<UserVoiceToTextHistory>(userVoiceToTextHistory, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<String>("You failed to upload [" + f.getAbsolutePath() + "] => " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<String>("You failed to upload " + name + " because the file was empty.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
