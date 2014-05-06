package edu.sjsu.cmpe283.lifechoices.webservices;

import edu.sjsu.cmpe283.lifechoices.entities.User;
import edu.sjsu.cmpe283.lifechoices.entities.UserGeoHistory;
import edu.sjsu.cmpe283.lifechoices.services.UserGeoHistoryService;

import edu.sjsu.cmpe283.lifechoices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * User: maksim
 * Date: 4/27/14 - 6:20 PM
 */
@RestController
@RequestMapping("/v1/user")
public class UserWS {


    @Autowired
    UserGeoHistoryService userGeoHistoryService;

    @Autowired
    UserService userService;

    /**
     * Web service to do batch user name change
     * @param uFrom User name to search
     * @param uTo Replacement user name
     * @return All saved users
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/username", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity updateUser( @RequestParam(value = "username-from", required = true) String uFrom,
                                      @RequestParam(value = "username-to", required = true) String uTo) {
        List<UserGeoHistory> all = userGeoHistoryService.findByUserName(uFrom);

        for (UserGeoHistory u : all) {
            u.setUserName(uTo);

        }

        List<UserGeoHistory> savd = userGeoHistoryService.save(all);


        return new ResponseEntity<List>(savd, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveUserFriends( @RequestParam(value = "uid", required = true) String uid,
                                           @RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "friends", required = true) String friends){

        User user = userService.find(uid);

        if(user == null){
            user = new User();
            user.setUsername(uid);
        }

        if(friends != null){
            List<String> strings = Arrays.asList(friends.split(","));
            user.setFriendIds(strings);
        }

        user.setName(name);

        user = userService.save(user);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getUser(@RequestParam(value = "uid", required = true) String uid){
        User user = userService.find(uid);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}
