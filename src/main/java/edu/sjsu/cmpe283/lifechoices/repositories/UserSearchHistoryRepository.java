package edu.sjsu.cmpe283.lifechoices.repositories;

import edu.sjsu.cmpe283.lifechoices.entities.UserSearchHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * User: maksim
 * Date: 5/4/14 - 10:33 AM
 */
public interface UserSearchHistoryRepository extends MongoRepository<UserSearchHistory, Long> {


}
