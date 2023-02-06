package com.techelevator.auctions.services;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.techelevator.auctions.model.Auction;

public class AuctionService {

    public static String API_BASE_URL = "http://localhost:3000/auctions/";
    private RestTemplate restTemplate = new RestTemplate();
    String url = API_BASE_URL;


    public Auction[] getAllAuctions() {
        Auction[] auctions = restTemplate.getForObject(url, Auction[].class);
        return auctions;
    }

    public Auction getAuction(int id) {
        String urlID = url + id;
        Auction auctions = restTemplate.getForObject(urlID, Auction.class);
        return auctions;
    }

    public Auction[] getAuctionsMatchingTitle(String title) {
        // call api here
        Auction[] auctions = restTemplate.getForObject(url+ "?title_like=" + title, Auction[].class);
        return auctions;
    }

    public Auction[] getAuctionsAtOrBelowPrice(double price) {
        Auction[] auctions = restTemplate.getForObject(url+ "?currentBid_lte=" + price,Auction[].class);

        return auctions;

     }

}
