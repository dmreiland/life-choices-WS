package edu.sjsu.cmpe283.lifechoices.services;

import edu.sjsu.cmpe283.lifechoices.entities.UserGeoHistory;
import edu.sjsu.cmpe283.lifechoices.entities.UserGeoHistoryType;
import edu.sjsu.cmpe283.lifechoices.repositories.UserGeoHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: maksim
 * Date: 3/12/14 - 9:47 PM
 */
@Service
public class UserGeoHistoryService {

    @Autowired
    UserGeoHistoryRepository userGeoHistoryRepository;

    /**
     * Save User Geo History
     * @param userGeoHistory Geo History object
     * @return saved object with id
     */
    public UserGeoHistory save(UserGeoHistory userGeoHistory){
        return userGeoHistoryRepository.save(userGeoHistory);
    }

    /**
     * Save Many User Geo Histories
     * @param userGeoHistory Geo History object
     * @return saved object with id
     */
    public List<UserGeoHistory> save(List<UserGeoHistory> userGeoHistory){
        return userGeoHistoryRepository.save(userGeoHistory);
    }

    /**
     * Find all user location history between given time
     * @param starttime Start time
     * @param endtime End time
     * @param username User name
     * @return User Geo history
     */
    public List<UserGeoHistory> findByTimestampBetweenAndUsername(long starttime, long endtime, String username){
        return userGeoHistoryRepository.findByTimestampBetweenAndUserName(starttime, endtime, username);
    }

    /**
     * Find all location history for a user
     * @param userName User name
     * @return all location history for a user
     */
    public List<UserGeoHistory> findByUserName(String userName){
        return userGeoHistoryRepository.findByUserName(userName);
    }


    /**
     * Finds all history locations in the database for a given circle AND between start and end time AND user
     * @return  all location history
     */
    public List<UserGeoHistory> findAll(){
        return userGeoHistoryRepository.findAll();
    }

    public Map<String, Object> findByPositionWithinAndTimestampBetweenAndUserName(Circle circle, long starttime, long endtime, String... userIds ){

        Map<String, Object> frieldsLocations = new HashMap<String, Object>();

        for(String uId : userIds) {
            List<UserGeoHistory> byPositionWithin = userGeoHistoryRepository.findByPositionWithinAndTimestampBetweenAndUserName(circle, starttime, endtime, uId);

            frieldsLocations.put(uId, byPositionWithin);
        }


        return frieldsLocations;
    }

    public List<UserGeoHistory> findByUserNameAndHistoryType(String userName, UserGeoHistoryType historyType){
        return userGeoHistoryRepository.findByUserNameAndHistoryType(userName, historyType);
    }


    public List<UserGeoHistory> findByUserNameAndYelpIdAndHistoryType(String userName, String yelpId, UserGeoHistoryType historyType){
        return userGeoHistoryRepository.findByUserNameAndYelpIdAndHistoryType(userName, yelpId, historyType);
    }

}
