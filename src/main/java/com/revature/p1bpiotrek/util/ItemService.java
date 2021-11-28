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

    public void writeItemToFile(String body) {

        String path = "C:\\Users\\Piortek\\IdeaProjects\\p1b-piotrek\\out\\output.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(body);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

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
}

