package edu.sjsu.cmpe283.lifechoices.services;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe283.lifechoices.utils.HTTPUtils;

@Service
public class MeetupService {

    private static Log logger = LogFactory.getLog(MeetupService.class);

    @Value("${meetup.api.key}")
    String MEETUP_API_KEY;
    
    @Value("${meetup.api.url}")
    String MEETUP_API_URL;
    
    private static final int MEETUP_RADIUS = 100; 
    
    
    public String getEvents(Double latitude, Double longitude, String category, String from, String to, String sort, Boolean sortDesc, String mode, Integer page, Integer radius) throws IOException {
        if(radius != null &&    radius > MEETUP_RADIUS) {
            radius = MEETUP_RADIUS;
        }
        
        String url =  String.format("%s/open_events?format=json&lat=%s&lon=%s&page=%s&time%s,%s&text_format=%s&order=%s&desc=%s%s%s&key=%s",MEETUP_API_URL,latitude,longitude,page,
                from, to, mode, sort, sortDesc, category != null ? "&category=" + category : "", radius != null ? "&radius=" + radius : "", MEETUP_API_KEY);
        
        logger.info(String.format("Fetching events with lat: '%s', long: '%s', category: '%s', page: '%s', radius: '%s'. URL: %s", latitude, longitude, category, page, radius, url));
        return HTTPUtils.HTTPGet(url);
    }
    
    
    
    
    public String getCategories() throws IOException {
        String url = String.format("%s/categories?format=json&key=%s", MEETUP_API_URL, MEETUP_API_KEY);
        logger.info("Fetching Event Categories: " + url);
        return HTTPUtils.HTTPGet(url);
    }
    
    
}

