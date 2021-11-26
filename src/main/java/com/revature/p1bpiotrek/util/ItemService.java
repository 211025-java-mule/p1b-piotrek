package com.revature.p1bpiotrek.util;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class ItemService {

    public void writeItemToFile(String body){

        String path = "C:\\Users\\Piortek\\IdeaProjects\\p1b-piotrek\\out\\output.txt";

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            bw.write(body);
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
    public String getBodyFromExternalEndpoint(String endpointName){
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
}
