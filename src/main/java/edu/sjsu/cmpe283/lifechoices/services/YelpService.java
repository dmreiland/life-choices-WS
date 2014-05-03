package edu.sjsu.cmpe283.lifechoices.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * User: srkarra
 * Date: 3/16/14 - 10:31 AM
 */
@Service
public class YelpService {

    private static Log logger = LogFactory.getLog(GooglePlacesService.class);

    private static final int MAX_AREA_SIZE = 40000;

    private OAuthService service;
    private Token accessToken;

    @Value("${yelp.token.key}")
    String YELP_TOKEN_KEY;

    @Value("${yelp.token.secret}")
    String YELP_TOKEN_SECRET;

    @Value("${yelp.consumer.key}")
    String YELP_CONSUMER_KEY;

    @Value("${yelp.consumer.secret}")
    String YELP_COSUMER_SECRET;

    @Value("${yelp.api.url}")
    String YELP_API_URL;

    @Value("${yelp.business.api.url}")
    String YELP_BUSINESS_API_URL;


    /**
     * Returns Yelp Businesses
     * @param searchTerm search term
     * @param latitude Latitude
     * @param longitude Location
     * @param radius radius filter for results
     * @param hasDeals - filter for results if they have deals available
     * @return Yelp JSON response
     */
    public String getPlaces(String searchTerm, double latitude, double longitude, Integer radius, String hasDeals ) {
        String response = "";
        // Configure OAuth
        if(service == null || accessToken == null) {
            this.service = new ServiceBuilder().provider(YelpServiceProvider.class).apiKey(YELP_CONSUMER_KEY).apiSecret(YELP_COSUMER_SECRET).build();
            this.accessToken = new Token(YELP_TOKEN_KEY, YELP_TOKEN_SECRET);
        }

        // Validate Radius
        if(radius == null || radius <= 0) {
            radius = 1610 * 3; // meters in three miles
        }
        else if(radius > MAX_AREA_SIZE) {
            radius = MAX_AREA_SIZE; // default to maximum = 25 miles
        }

        // Validate Deals
        if(hasDeals == null || (!hasDeals.equalsIgnoreCase("true") && !hasDeals.equalsIgnoreCase("false"))) {
            hasDeals = "false";
        }

        // Create and send request
        OAuthRequest request = new OAuthRequest(Verb.GET, YELP_API_URL);
        request.addQuerystringParameter("term", searchTerm == null ? "restaurants" : searchTerm);
        request.addQuerystringParameter("ll", latitude + "," + longitude);
        request.addQuerystringParameter("radius_filter", String.valueOf(radius));
        request.addQuerystringParameter("deals_filter", hasDeals);
        this.service.signRequest(this.accessToken, request);
        try {
            response =  request.send().getBody();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    /**
     * Returns Yelp Businesses
     * @param yelpId Yelp unique id
     * @return Yelp JSON response
     */
    public String getYelpById(String yelpId) {
        String response = "";
        // Configure OAuth
        if(service == null || accessToken == null) {
            this.service = new ServiceBuilder().provider(YelpServiceProvider.class).apiKey(YELP_CONSUMER_KEY).apiSecret(YELP_COSUMER_SECRET).build();
            this.accessToken = new Token(YELP_TOKEN_KEY, YELP_TOKEN_SECRET);
        }


        // Create and send request
        OAuthRequest request = new OAuthRequest(Verb.GET, YELP_BUSINESS_API_URL + "/" + yelpId);
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
