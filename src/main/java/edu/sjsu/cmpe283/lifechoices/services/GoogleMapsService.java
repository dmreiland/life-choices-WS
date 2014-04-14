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
import edu.sjsu.cmpe283.lifechoices.webservices.dto.domain.GoogleMapsDirections;

@Service
public class GoogleMapsService {
    
    private static Log logger = LogFactory.getLog(GoogleMapsService.class);
    
    @Value("${google.places.apikey}")
    String GOOGLE_PLACES_API_KEY;
    
    
    
    public List<SimpleEntry<Double, Double>> getRandomSeedDataForUpdates() {
        List<SimpleEntry<Double, Double>> seedData = new ArrayList<SimpleEntry<Double,Double>>();
        
        seedData.add(new SimpleEntry<Double, Double>(37.412985, -122.053472)); // MOFFETT FIELD
        seedData.add(new SimpleEntry<Double, Double>(37.3212995, -121.8696786 )); // Spartan Stadium
        seedData.add(new SimpleEntry<Double, Double>(37.6191050, -122.3752372)); // SFO Intl 
        seedData.add(new SimpleEntry<Double, Double>(37.3653473, -121.9157925)); // SJ Mineta Intl
        seedData.add(new SimpleEntry<Double, Double>(37.390052, -121.9781685)); // Great America
        return seedData;
    }
    
    
    
    
    
    public List<UpdatesDTO> getDirections(Double originLatitude, Double originLongitude) throws IOException {
        List<UpdatesDTO> updates = new ArrayList<UpdatesDTO>();
        
        for (SimpleEntry<Double, Double> seed : getRandomSeedDataForUpdates()) {
            // NOTE: I'm "hacking" here, should move this to an object instead of the SimpleEntry Object
            String originLat = String.valueOf(originLatitude);
            String originLong = String.valueOf(originLongitude);
            String destinationLatitude = String.valueOf(seed.getKey());
            String destinationLongitude = String.valueOf(seed.getValue());
            
            UpdatesDTO update = new UpdatesDTO();
            update.setOriginLatitude(originLatitude);
            update.setOriginLongitude(originLongitude);
            update.setDestinationLatitude(seed.getKey());
            update.setDestinationLatitude(seed.getValue());
            
            update.setRawDirections(getDirections(originLat, originLong, destinationLatitude, destinationLongitude));
            update.setGoogleMapsDistanceToDestination(update.getRawDirections().getRoutes().get(0).getLegs().get(0).getDistance().getText());
            update.setGoogleMapsTimeToDestination(update.getRawDirections().getRoutes().get(0).getLegs().get(0).getDuration().getText());
            
            updates.add(update);
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
    public GoogleMapsDirections getDirections(String originLatitude, String originLongitude, String destinationLatitude, String destinationLongitude) throws IOException {
        // Contruct URL
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
    
    
    
    
    
    
}
