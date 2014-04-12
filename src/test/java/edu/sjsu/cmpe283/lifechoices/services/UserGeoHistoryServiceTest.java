package edu.sjsu.cmpe283.lifechoices.services;

import edu.sjsu.cmpe283.lifechoices.LifeChoicesApp;
import edu.sjsu.cmpe283.lifechoices.entities.UserGeoHistory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * User: maksim
 * Date: 4/12/14 - 1:52 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LifeChoicesApp.class)
public class UserGeoHistoryServiceTest {

    @Autowired
    UserGeoHistoryService userGeoHistoryService;

    @Test
    public void testFindAll() throws Exception {

        List<UserGeoHistory> all = userGeoHistoryService.findAll();

        System.out.println("Size: " + all.size());
        for (UserGeoHistory userGeoHistory : all) {
            System.out.println("User: " + userGeoHistory.getUserName() + ", Location: " + userGeoHistory.getId() );
        }

    }
}
