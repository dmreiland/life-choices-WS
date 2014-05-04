package edu.sjsu.cmpe283.lifechoices.services;

import edu.sjsu.cmpe283.lifechoices.entities.UserSearchHistory;
import edu.sjsu.cmpe283.lifechoices.repositories.UserSearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: maksim
 * Date: 5/4/14 - 10:34 AM
 */
@Service
public class UserSearchHistoryService {

    @Autowired
    UserSearchHistoryRepository userSearchHistoryRepository;

    public UserSearchHistory save(UserSearchHistory ush){
        return userSearchHistoryRepository.save(ush);
    }


}
