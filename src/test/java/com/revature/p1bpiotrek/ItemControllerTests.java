package com.revature.p1bpiotrek;

import com.revature.p1bpiotrek.model.Item;
import com.revature.p1bpiotrek.util.ItemController;
import com.revature.p1bpiotrek.util.ItemService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemControllerTests {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemController itemController;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    public MockMvc mockMvc;

    @BeforeAll
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();

        mockMvc.perform(get("/items/name/piotrek"));
    }

    @Test
    public void testGetItems() throws Exception {
        this.mockMvc
                .perform(get("/items"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSavePersonWithName() throws Exception {
        this.mockMvc
                .perform(get("/items/name/piotrek"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("This name is already in DB")));

    }

    @Test
    public void testGetMostProbablyCountry() throws Exception {
        this.mockMvc
                .perform(get("/items/name/one/piotrek"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"country_id\":\"PL\",\"probability\":\"0.809871382859852\"}")));

    }

    @Test
    public void testSavingToFile() throws Exception {
        this.mockMvc
                .perform(get("/items/save"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("File saved to : \"C:\\Users\\Piortek\\IdeaProjects\\p1b-piotrek\\out\\output.txt\"")));

    }
    @Test
    public void testGetAllItems(){
        List<Item> items = itemService.getItems();

        Assertions.assertThat(items.size() == 1);

    }
    @Test
    public void testGetMatchingItemByName(){
        Item item = itemService.getMatchingItemByName("piotrek");
        Assertions.assertThat(item.getName().equals("piotrek"));

    }
}
