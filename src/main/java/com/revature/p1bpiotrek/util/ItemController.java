package com.revature.p1bpiotrek.util;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;


    @GetMapping("/hello")
    public String getHello() {
        return itemService.getBodyFromExternalEndpoint("hello");
    }

    @GetMapping("/items")
    public String getItems() {
        return itemService.getBodyFromExternalEndpoint("persons");
    }


}
