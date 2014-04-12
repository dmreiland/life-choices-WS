package edu.sjsu.cmpe283.lifechoices.services;

import org.junit.Test;

/**
 * User: maksim
 * Date: 4/12/14 - 3:05 PM
 */
public class APNServiceTest {
    @Test
    public void testSend() throws Exception {

        String tokeForRobsIphone = "4e0acbbb ca10ad61 5563d60d f52df7fd 079d606d d530e8c4 01b613c3 de3b54b0";

        APNService.send(tokeForRobsIphone, "test");
        APNService.closeConnection();
    }
}
