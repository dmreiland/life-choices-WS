package edu.sjsu.cmpe283.lifechoices.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.sjsu.cmpe283.lifechoices.entities.UserVoiceToTextHistory;
import edu.sjsu.cmpe283.lifechoices.repositories.UserVoiceToTextHistoryRepository;
import edu.sjsu.cmpe283.lifechoices.services.ATTSpeechToTextService;
import edu.sjsu.cmpe283.lifechoices.services.GooglePlacesService;
import edu.sjsu.cmpe283.lifechoices.services.UpdatesService;
import edu.sjsu.cmpe283.lifechoices.services.YelpService;
import org.json.JSONObject;
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
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    YelpService yelpService;

    @Autowired
    GooglePlacesService googlePlacesService;

    @Autowired
    UpdatesService updatesService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity voiceToText(@RequestParam(value = "q-voice", required = true) MultipartFile file,
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


    @RequestMapping(value = "search", method = RequestMethod.POST)
    public ResponseEntity voiceSearch(@RequestParam(value = "q-voice", required = true) MultipartFile file,
                                      @RequestParam(value = "uid", required = false, defaultValue = "1389900341294972") String userId,
                                      @RequestParam(value = "latitude", required = false, defaultValue = "37.334679") double latitude,
                                      @RequestParam(value = "longitude", required = false, defaultValue = "-121.881113") double longitude,
                                      @RequestParam(value = "radius", required = false, defaultValue = "4830") Integer radius
    ) {

        String name = "/tmp/voice-search-upload-" + new Date().getTime() + "-" + file.getOriginalFilename();
        File f = new File(name);
        Gson gson = new GsonBuilder().create();
        Map<String, Object> respMap = new HashMap<String, Object>();
        Object yelpFoodJson = "";
        String yelpFoodStr = null;
        Object yelpEventsJson = "";
        String yelpEventsStr = null;
        Object googleFoodJson = "";
        String googleFoodStr = null;
        Object googleEventsJson = "";
        String googleEventsStr = null;
        Object weatherAndTrafficJson = "";
        String weatherAndTrafficStr = null;

        String hasDeals = "false";

        if (!file.isEmpty()) {
            try {

                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(f));
                stream.write(bytes);
                stream.close();

                String transcribedText = attSpeechToTextService.getText(f);

                if (transcribedText != null) {
                    transcribedText = transcribedText.toLowerCase();
                }


                assert transcribedText != null;

                if (transcribedText.contains("deal")) {
                    hasDeals = "true";
                }

                if (transcribedText.contains("events") || transcribedText.contains("event")) {
                    yelpEventsStr = yelpService.getYelpResponseJson("events", latitude, longitude, radius, hasDeals);
                    yelpEventsJson = new JSONObject(yelpEventsStr);
                    googleEventsJson = googlePlacesService.getGooglePlaces("amusement_park|art_gallery|bowling_alley|movie_theater", latitude, longitude, radius);
                    googleEventsStr = gson.toJson(googleEventsJson);
                }

                if (transcribedText.contains("food") || transcribedText.contains("restaurant")) {
                    yelpFoodStr = yelpService.getYelpResponseJson("restaurants", latitude, longitude, radius, hasDeals);
                    yelpFoodJson = new JSONObject(yelpFoodStr);
                    googleFoodJson = googlePlacesService.getGooglePlaces("bakery|bar|restaurant|food|funeral_home|meal_delivery|meal_takeaway|grocery_or_supermarket", latitude, longitude, radius);
                    googleFoodStr = gson.toJson(googleFoodJson);
                }

                if (transcribedText.contains("weather") || transcribedText.contains("traffic")) {
                    weatherAndTrafficJson = updatesService.getUpdatesV2(true, latitude, longitude, "imperial", 3, 640, 480);
                    weatherAndTrafficStr = gson.toJson(weatherAndTrafficJson);
                }

                UserVoiceToTextHistory userVoiceToTextHistory = new UserVoiceToTextHistory();
                userVoiceToTextHistory.setTimestamp(new Date());
                userVoiceToTextHistory.setUserName(userId);
                userVoiceToTextHistory.setTranscribedText(transcribedText);
                userVoiceToTextHistoryRepository.save(userVoiceToTextHistory);

                f.delete();

                respMap.put("yelp-food", yelpFoodJson);
                respMap.put("yelp-events", yelpEventsJson);
                respMap.put("google-food", googleFoodJson);
                respMap.put("google-events", googleEventsJson);
                respMap.put("weather-traffic", weatherAndTrafficJson);

//                return new ResponseEntity<Map>(respMap, HttpStatus.OK);
                String responseStr;
                responseStr = "{";

                if (yelpFoodStr != null) {
                    responseStr += "\"yelp-food\":" + yelpFoodStr + ",";
                } else {
                    responseStr += "\"yelp-food\":null,";
                }

                if (yelpEventsStr != null) {
                    responseStr += "\"yelp-events\":" + yelpEventsStr + ",";
                } else {
                    responseStr += "\"yelp-events\":null,";
                }

                if (googleFoodStr != null) {
                    responseStr += "\"google-food\":" + googleFoodStr + ",";
                } else {
                    responseStr += "\"google-food\":null,";
                }

                if (googleEventsStr != null) {
                    responseStr += "\"google-events\":" + googleEventsStr + ",";
                } else {
                    responseStr += "\"google-events\":null,";
                }

                if (weatherAndTrafficStr != null) {
                    responseStr += "\"weather-traffic\":" + weatherAndTrafficStr;
                } else {
                    responseStr += "\"weather-traffic\":null";
                }

                responseStr += "}";
                return new ResponseEntity<String>(responseStr, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                f.delete();
                return new ResponseEntity<String>("You failed to upload [" + f.getAbsolutePath() + "] => " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            f.delete();
            return new ResponseEntity<String>("You failed to upload " + name + " because the file was empty.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}