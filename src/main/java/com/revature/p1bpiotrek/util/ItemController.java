package com.revature.p1bpiotrek.util;

import com.revature.p1bpiotrek.model.Item;
import com.revature.p1bpiotrek.model.Piece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public String checkNationalityByName(@PathVariable("name") String name) {
       return itemService.getBodyFromExternalEndpoint("persons/name/" + name);

    }

    @GetMapping("/items/name/one/{name}")
    public Piece getMostProbablyCountry(@PathVariable("name") String name) {

        Item matchingItem = itemService.getMatchingItem(name);
        if (matchingItem.getName() == null) {
            System.err.println("Before checking please add this name: " + name + " to DB");
            return null;
        } else {

            List<Piece> countries = matchingItem.getCountries();
            return countries.get(0);
        }
    }
}
