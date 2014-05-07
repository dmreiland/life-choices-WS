package edu.sjsu.cmpe283.lifechoices.services;

import edu.sjsu.cmpe283.lifechoices.entities.User;
import edu.sjsu.cmpe283.lifechoices.entities.UserGeoHistory;
import edu.sjsu.cmpe283.lifechoices.entities.UserGeoHistoryType;
import edu.sjsu.cmpe283.lifechoices.repositories.UserRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * User: maksim
 * Date: 3/12/14 - 9:47 PM
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserGeoHistoryService userGeoHistoryService;

    /**
     * Save user
     * @param user New user
     * @return saved user
     */
    public User save(User user){
        return userRepository.save(user);
    }

    /**
     * Find a user
     * @param userId User Id
     * @return User
     */
    public User find(String userId){
        return userRepository.findOne(userId);
    }


    public JSONObject addPropsToJson(String strJson, String userId) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(strJson);
        JSONObject jsonObject = (JSONObject) obj;
        jsonObject.put("user-id", userId);
        jsonObject.put("user-info", find(userId));

        JSONArray businesses = (JSONArray) jsonObject.get("businesses");

        Iterator<JSONObject> iterator = businesses.iterator();
        while (iterator.hasNext()) {
            JSONObject businessJsonObj = iterator.next();

            String yelpBusinessId = (String) businessJsonObj.get("id");
            System.out.println(yelpBusinessId);


            List<UserGeoHistory> checkedInYelpPlaces = userGeoHistoryService.findByUserNameAndYelpIdAndHistoryType(userId, yelpBusinessId, UserGeoHistoryType.CHECKIN);

            businessJsonObj.put("num-of-visits", checkedInYelpPlaces.size());
            businessJsonObj.put("has-been-visited", checkedInYelpPlaces.size() > 0);
        }


        return jsonObject;
    }
}
