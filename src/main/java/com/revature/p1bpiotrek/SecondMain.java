package com.revature.p1bpiotrek;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1bpiotrek.model.Country;
import com.revature.p1bpiotrek.model.Item;
import com.revature.p1bpiotrek.util.ItemService;
import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SecondMain {
    public static void main(String[] args) throws IOException {
        ItemService itemService = new ItemService();
        ObjectMapper objectMapper = new ObjectMapper();

        URL url = new URL("http://localhost:8081/persons/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        InputStream response = connection.getInputStream();


        String body = new String(response.readAllBytes());

        //System.out.println(body);

        JSONArray jsonArray = new JSONArray(body);

        for(Object o : jsonArray){
            String countryString = o.toString();
            JsonNode countryNode = objectMapper.readTree(countryString).path("countries");
            String stringC = countryNode.toString().trim().replace("\\", "");
            //System.out.println(stringC);
            //List<Country> list = objectMapper.readValue(stringC , new TypeReference<>(){});
            break;
        }

        String string = "\"[{\"country_id\":\"PL\",\"probability\":0.809871382859852},{\"country_id\":\"IE\",\"probability\":0.06526996695870484},{\"country_id\":\"\",\"probability\":0.04293796263527029}]\"";



        String substring = string.substring(1, string.length()-1);

        List<Country> list = objectMapper.readValue(substring , new TypeReference<>(){});

        list.forEach(System.out::println); //FUCK YES


    }
}


