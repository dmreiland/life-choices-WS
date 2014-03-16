package edu.sjsu.cmpe283.lifechoices.services;

import edu.sjsu.cmpe283.lifechoices.LifeChoicesApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * User: maksim
 * Date: 3/12/14 - 11:58 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = LifeChoicesApp.class)
public class UserServiceTest {

    @Autowired
    UserService userService;


    @Test
    public void testSave() throws Exception {
    }

    @Test
    public void testFind() throws Exception {

    }

}
