package com.revature.p1bpiotrek.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1bpiotrek.model.Item;
import com.revature.p1bpiotrek.model.Piece;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    ObjectMapper objectMapper;

    /**
     * @param body Method consumes String then writes it to the file with given path
     */
    public void writeItemToFile(String body) {

        String path = "C:\\Users\\Piortek\\IdeaProjects\\p1b-piotrek\\out\\output.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(body);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * @param endpointName Method accepts String endpoint and performs GET method to localhost:8081
     * @return Then response of this method is returned as a String object
     */
    public String getBodyFromExternalEndpoint(String endpointName) {
        URL url = null;
        try {
            url = new URL("http://localhost:8081/" + endpointName);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream response = null;
        try {
            response = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String body = null;
        try {
            body = new String(response.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    /**
     * Method performs GET method on localhost:8081/persons/, so it takes all Person object from this endpoint
     * Parsing and creating Item object is also added here, method is using Jackson
     *
     * @return List of Item object is returned
     */
    public List<Item> getItems() {
        URL url = null;
        try {
            url = new URL("http://localhost:8081/persons/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream response = null;
        try {
            response = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String body = null;
        try {
            body = new String(response.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray(body);
        List<Item> itemList = new ArrayList<>();
        for (Object o : jsonArray) {
            Item item = new Item();

            String nameString = o.toString();
            JsonNode nameNode = null;
            try {
                nameNode = objectMapper.readTree(nameString).path("name");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            String name = nameNode.toString().replace("\"", "");
            item.setName(name);

            String countryString = o.toString();
            JsonNode countryNode = null;
            try {
                countryNode = objectMapper.readTree(countryString).path("countries");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            String stringC = countryNode.toString().trim().replace("\\", "");

            String substring = stringC.substring(1, stringC.length() - 1);
            List<Piece> list = null;
            try {
                list = objectMapper.readValue(substring, new TypeReference<>() {
                });
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            item.setCountries(list);
            itemList.add(item);
        }
        return itemList;
    }

    /**
     * Method first does getItems(), then checks if input name is present in received List
     *
     * @param name Method consumes String name as a parameter, then uses it to check if there is Item with that name in the List
     * @return Item with input name is returned if there is a match, if not Item object with fields set tu null is returned
     */
    public Item getMatchingItemByName(String name) {
        List<Item> items = getItems();
        Item item = new Item();

        for (Item item1 : items) {
            if (item1.getName().equals(name)) {
                item = item1;
            }
        }
        return item;
    }
}

