package edu.sjsu.cmpe283.lifechoices.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.sjsu.cmpe283.lifechoices.webservices.dto.UpdatesDTO;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.UpdatesDTO.Destinations;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.domain.GoogleMapsDirections;

@Service
public class UpdatesService {
    
    private static Log logger = LogFactory.getLog(UpdatesService.class);
    
    @Value("${google.places.apikey}")
    String GOOGLE_PLACES_API_KEY;
    
    
    /**
     * TODO REMOVE
     * @return
     */
    public List<SimpleEntry<Double, Double>> getRandomSeedDataForUpdates() {
        List<SimpleEntry<Double, Double>> seedData = new ArrayList<SimpleEntry<Double,Double>>();
        
        seedData.add(new SimpleEntry<Double, Double>(37.412985, -122.053472)); // MOFFETT FIELD
        seedData.add(new SimpleEntry<Double, Double>(37.3212995, -121.8696786 )); // Spartan Stadium
        seedData.add(new SimpleEntry<Double, Double>(37.6191050, -122.3752372)); // SFO Intl 
        seedData.add(new SimpleEntry<Double, Double>(37.3653473, -121.9157925)); // SJ Mineta Intl
        seedData.add(new SimpleEntry<Double, Double>(37.390052, -121.9781685)); // Great America
        return seedData;
    }
    
    
    
    
    /**
     * Returns the directions/maps link/weather for all of the user's location 
     * 
     * 
     * @param originLatitude
     * @param originLongitude
     * @param zoom
     * @param width
     * @param height
     * @return
     * @throws IOException
     */
    public UpdatesDTO getDirections(Double originLatitude, Double originLongitude, Integer width, Integer height) throws IOException {
        UpdatesDTO updates = new UpdatesDTO();
        updates.setLatitude(originLatitude);
        updates.setLongitude(originLongitude);
        
        for (SimpleEntry<Double, Double> seed : getRandomSeedDataForUpdates()) {
            // NOTE: I'm "hacking" here, should move this to an object instead of the SimpleEntry Object
            Destinations destination = new Destinations();
            destination.setLatitude(seed.getKey());
            destination.setLongitude(seed.getValue());
            
            // Get directions
            destination.setRawDirections(getGoogleDirections(originLatitude, originLongitude, seed.getKey(), seed.getValue()));
            destination.setDistanceToDestination(destination.getRawDirections().getRoutes().get(0).getLegs().get(0).getDistance().getText());
            destination.setTimeToDestination(destination.getRawDirections().getRoutes().get(0).getLegs().get(0).getDuration().getText());
            
            // Get Static Map
            destination.setGoogleMapsStaticLink(getGoogleStaticMapsURL(width, height, originLatitude, originLongitude, seed.getKey(), seed.getValue(), destination.getRawDirections().getRoutes().get(0).getPolyLine().getPoints()));
            
            
            // Store Destination
            updates.addDesination(destination);
        }
        
        return updates;
    }
    
    
    
    
    
    
    
    
    /**
     * Fetches the direction
     * 
     * 
     * Sample Directions: 
     * 
     * San Jose State Lat/Long: 37.334679,-121.881113
     * Moffett Field Lat/Long: 37.412985, -122.053472
     * 
     * 
     * @param originLatitude
     * @param originLongitude
     * @param destinationLatitude
     * @param destinationLongitude
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public GoogleMapsDirections getGoogleDirections(double originLatitude, double originLongitude, double destinationLatitude, double destinationLongitude) throws IOException {
        // Construct URL
        String directionURL = String.format("https://maps.googleapis.com/maps/api/directions/json?sensor=false&key=%s&origin=%s,%s&destination=%s,%s", GOOGLE_PLACES_API_KEY, originLatitude, originLongitude, destinationLatitude, destinationLongitude);
        
        // Send GET Request
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet get = new HttpGet(directionURL);
        CloseableHttpResponse response = httpclient.execute(get);
        
        // Get String
        String data = "";
        try {
            HttpEntity entity = response.getEntity();
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                data += line;
            } 
            
            data = data.replaceAll("\\s+", " ");
            EntityUtils.consumeQuietly(entity);
        }
        finally{
            response.close();
        }
        
        
        // Construct POJO
        GoogleMapsDirections directions = new ObjectMapper()
//                .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
//                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .readValue(data, GoogleMapsDirections.class);
        
        return directions;
    }
    
    
    
    
    /**
     * Construct the url for Google Maps Static images
     * @param width
     * @param height
     * @param originLat
     * @param originLong
     * @param destinationLat
     * @param destinationLong
     * @param points
     * @return
     */
    public String getGoogleStaticMapsURL(Integer width, Integer height, double originLat, double originLong, double destinationLat, double destinationLong, String points) {
        String url = String.format("http://maps.google.com/maps/api/staticmap?key=%s&size=%dx%d&maptype=roadmap&sensor=false&markers=%s,%s|%s,%s&path=weight:3|color:blue|enc:%s"
                , GOOGLE_PLACES_API_KEY, width, height, originLat, originLong, destinationLat, destinationLong, points);
        return url;
    }
    
}
