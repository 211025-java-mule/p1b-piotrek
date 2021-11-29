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

    /**
     * @return Method returns all Items from external endpoint
     */
    @GetMapping("/items")
    public String getItems() {
        return itemService.getBodyFromExternalEndpoint("persons");
    }

    /**
     * Method adds Item object with given name to external repository. Repository does not accept duplicates
     *
     * @return Information about completing this task is returned
     */
    @GetMapping("/items/name/{name}")
    public String checkNationalityByName(@PathVariable("name") String name) {
        return itemService.getBodyFromExternalEndpoint("persons/name/" + name);

    }

    /**
     * Method performs GET method on external endpoint with given name. If name is not yet in DB information about this will be returned
     *
     * @param name Method consumes String name as a parameter to search in external DB for it
     * @return Method returns Piece object, that contains the biggest probability of name nationality
     */
    @GetMapping("/items/name/one/{name}")
    public Piece getMostProbablyCountry(@PathVariable("name") String name) {

        Item matchingItem = itemService.getMatchingItemByName(name);
        if (matchingItem.getName() == null) {
            System.err.println("Before checking please add this name: " + name + " to DB");
            return null;
        } else {

            List<Piece> countries = matchingItem.getCountries();
            return countries.get(0);
        }
    }

    /**
     * Method consumes String then writes it to the file with given path
     *
     * @return Method returns String with information about path that file has been saved
     */
    @GetMapping("/items/save")
    public String writeAllItemsToOutputFile() {
        String body = itemService.getBodyFromExternalEndpoint("persons");
        itemService.writeItemToFile(body);

        return "File saved to : \"C:\\Users\\Piortek\\IdeaProjects\\p1b-piotrek\\out\\output.txt\"";

    }
}

