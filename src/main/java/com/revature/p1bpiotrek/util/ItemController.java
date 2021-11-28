package com.revature.p1bpiotrek.util;

import com.revature.p1bpiotrek.model.Item;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/items")
    public String getItems() {
        return itemService.getBodyFromExternalEndpoint("persons");
    }
    @GetMapping("/items/name/{name}")
    public Item checkNationalityByName(@PathVariable("name") String name){
        itemService.getBodyFromExternalEndpoint("persons/name/" + name);
        List<Item> items = itemService.getItems();
        Item item = new Item();

        for(Item item1 : items){
            if (item1.getName().equals(name)){
                item = item1;
            }
        }
        return item;
    }




}
