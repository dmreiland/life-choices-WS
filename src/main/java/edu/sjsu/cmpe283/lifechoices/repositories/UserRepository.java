package edu.sjsu.cmpe283.lifechoices.repositories;

import edu.sjsu.cmpe283.lifechoices.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * User: maksim
 * Date: 3/12/14 - 9:45 PM
 */
public interface UserRepository extends MongoRepository<User, Long> {
}
