package edu.sjsu.cmpe283.lifechoices.services;

import org.scribe.model.Token;
import org.scribe.builder.api.DefaultApi10a;

/**
 * Yelp Service Provider based on https://github.com/Yelp/yelp-api/blob/master/v2/java/YelpApi2.java
 * 
 * User: srkarra
 * Date: 3/16/14 - 4:00 PM
 */
public class YelpServiceProvider extends DefaultApi10a {
    
    @Override
    public String getAccessTokenEndpoint() {
        return null;
    }
    
    @Override
    public String getAuthorizationUrl(Token arg0) {
        return null;
    }
    
    @Override
    public String getRequestTokenEndpoint() {
        return null;
    }
    
}

