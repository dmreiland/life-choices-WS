package edu.sjsu.cmpe283.lifechoices.repositories;

import edu.sjsu.cmpe283.lifechoices.entities.UserGeoHistory;
import edu.sjsu.cmpe283.lifechoices.entities.UserGeoHistoryType;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.data.mongodb.core.geo.Distance;
import org.springframework.data.mongodb.core.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.*;
import java.util.List;

/**
 * User: maksim
 * Date: 3/12/14 - 6:12 PM
 */
public interface UserGeoHistoryRepository extends MongoRepository<UserGeoHistory, Long> {


    public List<UserGeoHistory> findByTimestampBetweenAndUserName(long starttime, long endtime, String userName);

    public List<UserGeoHistory> findByPositionWithinAndTimestampBetweenAndUserName(Circle c, long starttime, long endtime, String userName);

    public List<UserGeoHistory> findByUserName(String userName);

    public List<UserGeoHistory> findByPositionNear(Point p, Distance d);

    public List<UserGeoHistory> findByPositionWithin(Circle c);

    public List<UserGeoHistory> findByPositionWithin(Box b);

    public List<UserGeoHistory> findByUserNameAndHistoryType(String userName, UserGeoHistoryType historyType);


}
