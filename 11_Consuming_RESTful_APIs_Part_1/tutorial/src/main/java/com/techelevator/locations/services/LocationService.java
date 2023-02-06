package com.techelevator.locations.services;

import com.techelevator.locations.model.Location;
import org.springframework.web.client.RestTemplate;

public class LocationService {
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String API_BASE_URL = "http://localhost:3000/locations/";

    public Location[] getAll() {
        return restTemplate.getForObject(API_BASE_URL, Location[].class);
        //Step Five: List all locations
    }

    public Location getOne(int id) {
        //Step Six: Get location details
        return null;
    }

}
