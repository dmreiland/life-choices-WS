package edu.sjsu.cmpe283.lifechoices.repositories;

import edu.sjsu.cmpe283.lifechoices.entities.UserGeoHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * User: maksim
 * Date: 3/12/14 - 6:12 PM
 */
public interface UserGeoHistoryRepository extends MongoRepository<UserGeoHistory, Long> {
}
