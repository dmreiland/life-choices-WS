package edu.sjsu.cmpe283.lifechoices.services;

import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.sjsu.cmpe283.lifechoices.utils.HTTPUtils;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.UpdatesDTO;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.UpdatesDTOV2;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.UpdatesDTO.Destinations;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.UpdatesDTOV2.DestinationsV2;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.domain.GoogleMapsDirections;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.domain.Weather;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.domain.WeatherV2;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.domain.WeatherV2.Conditions;
import edu.sjsu.cmpe283.lifechoices.webservices.dto.domain.WeatherV2.Forecast;

@Service
public class UpdatesService {
    
    private static Log logger = LogFactory.getLog(UpdatesService.class);
    
    @Value("${google.places.apikey}")
    String GOOGLE_PLACES_API_KEY;
    
    @Value("${weather.apikey}")
    String WUNDERGROUND_API;
    
    
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
    public UpdatesDTO getUpdates(Double originLatitude, Double originLongitude, Integer width, Integer height) throws IOException {
        UpdatesDTO updates = new UpdatesDTO();
        updates.setLatitude(originLatitude);
        updates.setLongitude(originLongitude);
        updates.setWeather(getWeatherData(originLatitude, originLongitude));
        
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
            
            // Get Weather Info
            destination.setWeather(getWeatherData(seed.getKey(), seed.getValue()));
            
            // Store Destination
            updates.addDesination(destination);
        }
        
        return updates;
    }
    
    
    
    /**
     * Returns the directions/maps link/weather for all of the user's location
     * 
     * @param getMaps
     * @param originLatitude
     * @param originLongitude
     * @param units
     * @param forecastCount
     * @param width
     * @param height
     * @return
     * @throws IOException
     */
    public UpdatesDTOV2 getUpdatesV2(Boolean getMaps, Double originLatitude, Double originLongitude, String units, Integer forecastCount, Integer width, Integer height) throws IOException {
        UpdatesDTOV2 updates = new UpdatesDTOV2();
        updates.setLatitude(originLatitude);
        updates.setLongitude(originLongitude);
        updates.setWeather(getWeatherDataV2(originLatitude, originLongitude, units, forecastCount));
        
        for (SimpleEntry<Double, Double> seed : getRandomSeedDataForUpdates()) {
            // NOTE: I'm "hacking" here, should move this to an object instead of the SimpleEntry Object
            DestinationsV2 destination = new DestinationsV2();
            destination.setLatitude(seed.getKey());
            destination.setLongitude(seed.getValue());
            
            if(getMaps) {
                // Get directions
                destination.setRawDirections(getGoogleDirections(originLatitude, originLongitude, seed.getKey(), seed.getValue()));
                destination.setDistanceToDestination(destination.getRawDirections().getRoutes().get(0).getLegs().get(0).getDistance().getText());
                destination.setTimeToDestination(destination.getRawDirections().getRoutes().get(0).getLegs().get(0).getDuration().getText());
                
                // Get Static Map
                destination.setGoogleMapsStaticLink(getGoogleStaticMapsURL(width, height, originLatitude, originLongitude, seed.getKey(), seed.getValue(), destination.getRawDirections().getRoutes().get(0).getPolyLine().getPoints()));
            }
            
            // Get Weather Info
            destination.setWeather(getWeatherDataV2(seed.getKey(), seed.getValue(), units, forecastCount));
            
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
        String data = HTTPUtils.HTTPGet(directionURL);
        
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
    
    
    
    
    
    
    
    
    
    
    public Weather getWeatherData(Double latitude, Double longitude) throws IOException {
        String weatherURL = String.format("http://api.wunderground.com/api/%s/forecast/q/%s,%s.json", WUNDERGROUND_API, latitude, longitude);
        String data = HTTPUtils.HTTPGet(weatherURL);
        
        Weather weather =  new ObjectMapper()
//              .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
              .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
//              .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
              .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
              .readValue(data, Weather.class);
        return weather;
    }
    
    
    public WeatherV2 getWeatherDataV2(Double latitude, Double longitude, String units, Integer forecastCount) throws IOException {
        WeatherV2 weather = new WeatherV2();
        
        String conditionsURL = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s", latitude, longitude);
        String forecastURL = String.format("http://api.openweathermap.org/data/2.5/forecast/daily?lat=%s&lon=%s&units=%s&cnt=%s", latitude, longitude, units, forecastCount);
        
        logger.info("Conditions URL: " + conditionsURL);
        logger.info("Forecast   URL: " + forecastURL);
        
        ObjectMapper mapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        weather.setConditions(mapper.readValue(HTTPUtils.HTTPGet(conditionsURL), Conditions.class));
        weather.setForecast(mapper.readValue(HTTPUtils.HTTPGet(forecastURL), Forecast.class));
        return weather;
    }
    
}

