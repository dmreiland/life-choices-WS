package edu.sjsu.cmpe283.lifechoices.services;

import com.googlecode.placesapiclient.client.entity.Place;
import org.junit.Test;

import java.util.List;

/**
 * User: maksim
 * Date: 3/16/14 - 10:47 AM
 */
public class GooglePlacesServiceTest {

    GooglePlacesService googlePlacesService = new GooglePlacesService();

    @Test
    public void testGetGooglePlaces() throws Exception {
        List<Place> places = googlePlacesService.getGooglePlaces("restaurant", -33.8665433, 151.1956316, 500);

        for (Place p : places) {
            System.out.println(p.getId() + " - " + p.getName());
        }

    }
}
