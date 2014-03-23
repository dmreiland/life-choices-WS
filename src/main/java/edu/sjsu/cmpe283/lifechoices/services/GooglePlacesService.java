package edu.sjsu.cmpe283.lifechoices.services;

import com.googlecode.placesapiclient.client.argument.ArgumentMap;
import com.googlecode.placesapiclient.client.argument.helper.ArgumentMapHelper;
import com.googlecode.placesapiclient.client.entity.Place;
import com.googlecode.placesapiclient.client.exception.ErrorCodeException;
import com.googlecode.placesapiclient.client.service.PlacesService;
import com.googlecode.placesapiclient.client.service.impl.PlacesServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: maksim
 * Date: 3/16/14 - 10:31 AM
 */
@Service
public class GooglePlacesService {

    private static Log logger = LogFactory.getLog(GooglePlacesService.class);

    @Value("${google.places.apikey}")
    String GOOGLE_PLACES_API_KEY;

    /**
     *
     * @param type Place type
     * @param lat Latitude
     * @param lon Longitude
     * @param radius The distance (in meters) within which to return Place results
     * @return Places for a give location
     * @throws ErrorCodeException
     */
    public List<Place> getGooglePlaces(String type, double lat, double lon, Integer radius) {
        PlacesService placesService = new PlacesServiceImpl(GOOGLE_PLACES_API_KEY);
        placesService.init();

        // Prepare search arguments
        ArgumentMap argumentMap = ArgumentMapHelper.prepareArgumentMapForPlaceRadarSearch(placesService.getApiKey(), type, lat, lon, radius, false);

        // Invoke service with argumentMap
        List<Place> placeList = new ArrayList<Place>();

        try {
            placeList = placesService.placeNearbySearchRequest(argumentMap);
        } catch (ErrorCodeException e) {
            if(! e.getMessage().contains("ZERO_RESULTS")){
                e.printStackTrace();
            }
        }

        return placeList;
    }
}
