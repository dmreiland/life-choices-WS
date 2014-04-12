package edu.sjsu.cmpe283.lifechoices.jobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * User: maksim
 * Date: 4/12/14 - 3:28 PM
 */
@Service
public class ScheduledJobs {

    @Scheduled(fixedDelay = 20000) // 20 sec
    public void trafficScheduler(){

    }
}
