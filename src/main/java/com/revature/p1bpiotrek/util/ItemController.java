package com.revature.p1bpiotrek.util;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class ItemController {

    @SneakyThrows
    @GetMapping("/hello")
    public String getHello() throws IOException {
        URL url = new URL("http://localhost:8081/hello");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream response = connection.getInputStream();
        return new String(response.readAllBytes());
    }
}
