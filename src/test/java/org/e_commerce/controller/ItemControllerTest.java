package org.e_commerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.e_commerce.dto.ItemCreateDTO;
import org.e_commerce.dto.ItemResponseDTO;
import org.e_commerce.dto.ItemUpdateDTO;
import org.e_commerce.service.ItemService;
import org.e_commerce.Exception.DuplicateItemException;
import org.e_commerce.Exception.ItemNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ItemService service;

    // Reset mocks before each test
    @BeforeEach
    void setup() {
        reset(service);
    }

    // --------- CREATE ITEM SUCCESS ----------
    @Test
    void createItem_validInput_shouldReturn201() throws Exception {
        ItemCreateDTO dto = new ItemCreateDTO(1, "Laptop", "Gaming Laptop");
        ItemResponseDTO response = new ItemResponseDTO(1, "Laptop", "Gaming Laptop");

        Mockito.when(service.addItem(any(ItemCreateDTO.class))).thenReturn(response);

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Laptop"))
                .andExpect(jsonPath("$.description").value("Gaming Laptop"));
    }

    // --------- CREATE ITEM INVALID NAME ----------
    @Test
    void createItem_invalidName_shouldReturn400() throws Exception {
        String json = """
    {
      "id": 1,
      "name": "",
      "description": "Gaming Laptop"
    }
    """;

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
    // --------- CREATE ITEM DUPLICATE ID ----------
    @Test
    void createItem_duplicateId_shouldReturn409() throws Exception {
        ItemCreateDTO dto = new ItemCreateDTO(1, "Laptop", "Gaming Laptop");

        Mockito.when(service.addItem(any(ItemCreateDTO.class)))
                .thenThrow(new DuplicateItemException("Item with id 1 already exists"));

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("Item with id 1 already exists"));
    }

    // --------- GET ITEM BY ID SUCCESS ----------
    @Test
    void getItemById_shouldReturn200() throws Exception {
        ItemResponseDTO response = new ItemResponseDTO(1, "Laptop", "Gaming Laptop");

        Mockito.when(service.getItemById(1)).thenReturn(response);

        mockMvc.perform(get("/items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Laptop"))
                .andExpect(jsonPath("$.description").value("Gaming Laptop"));
    }

    // --------- GET ITEM BY ID NOT FOUND ----------
    @Test
    void getItemById_notFound_shouldReturn404() throws Exception {
        Mockito.when(service.getItemById(1))
                .thenThrow(new ItemNotFoundException("Item with id 1 not found"));

        mockMvc.perform(get("/items/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Item with id 1 not found"));
    }

    // --------- UPDATE ITEM SUCCESS ----------
    @Test
    void updateItem_validInput_shouldReturn200() throws Exception {
        ItemUpdateDTO dto = new ItemUpdateDTO("Updated Laptop", "New Description");
        ItemResponseDTO response = new ItemResponseDTO(1, "Updated Laptop", "New Description");

        Mockito.when(service.updateItem(Mockito.eq(1), any(ItemUpdateDTO.class)))
                .thenReturn(response);

        mockMvc.perform(put("/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Laptop"))
                .andExpect(jsonPath("$.description").value("New Description"));
    }

    // --------- UPDATE ITEM NOT FOUND ----------
    @Test
    void updateItem_notFound_shouldReturn404() throws Exception {
        ItemUpdateDTO dto = new ItemUpdateDTO("Updated Laptop", "New Description");

        Mockito.when(service.updateItem(Mockito.eq(1), any(ItemUpdateDTO.class)))
                .thenThrow(new ItemNotFoundException("Item with id 1 not found"));

        mockMvc.perform(put("/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Item with id 1 not found"));
    }

    // --------- DELETE ITEM SUCCESS ----------
    @Test
    void deleteItem_shouldReturn204() throws Exception {
        Mockito.doNothing().when(service).deleteItem(1);

        mockMvc.perform(delete("/items/1"))
                .andExpect(status().isNoContent());
    }

    // --------- DELETE ITEM NOT FOUND ----------
    @Test
    void deleteItem_notFound_shouldReturn404() throws Exception {
        Mockito.doThrow(new ItemNotFoundException("Item with id 1 not found"))
                .when(service).deleteItem(1);

        mockMvc.perform(delete("/items/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Item with id 1 not found"));
    }
}
