package edu.sjsu.cmpe283.lifechoices.services;

import edu.sjsu.cmpe283.lifechoices.entities.User;
import edu.sjsu.cmpe283.lifechoices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: maksim
 * Date: 3/12/14 - 9:47 PM
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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
    public User find(Long userId){
        return userRepository.findOne(userId);
    }
}
