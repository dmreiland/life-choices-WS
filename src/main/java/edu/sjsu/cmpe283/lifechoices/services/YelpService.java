package edu.sjsu.cmpe283.lifechoices.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe283.lifechoices.config.Props;

/**
 * User: srkarra
 * Date: 3/16/14 - 10:31 AM
 */
@Service
public class YelpService {
    
    private static Log logger = LogFactory.getLog(GooglePlacesService.class);
    
    private OAuthService service;
    private Token accessToken;
    
    /**
     * Returns Yelp Businesses
     * @param searchTerm search term
     * @param latitude Latitude
     * @param longitude Location
     * @return Yelp JSON response
     */
    public String getFoodPlaces(String searchTerm, double latitude, double longitude) {
        String response = "";
        // Configure OAuth
        if(service == null || accessToken == null) {
            this.service = new ServiceBuilder().provider(YelpServiceProvider.class).apiKey(Props.YELP_CONSUMER_KEY).apiSecret(Props.YELP_COSUMER_SECRET).build();
            this.accessToken = new Token(Props.YELP_TOKEN_KEY, Props.YELP_TOKEN_SECRET);
        }
        
        // Create and send request
        OAuthRequest request = new OAuthRequest(Verb.GET, Props.YELP_API_URL);
        request.addQuerystringParameter("term", searchTerm == null ? "restaurants" : searchTerm);
        request.addQuerystringParameter("ll", latitude + "," + longitude);
        this.service.signRequest(this.accessToken, request);
        try {
            response =  request.send().getBody();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
