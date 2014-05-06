package edu.sjsu.cmpe283.lifechoices.repositories;

import edu.sjsu.cmpe283.lifechoices.entities.UserVoiceToTextHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * User: maksim
 * Date: 5/4/14 - 10:33 AM
 */
public interface UserVoiceToTextHistoryRepository extends MongoRepository<UserVoiceToTextHistory, Long> {


}
