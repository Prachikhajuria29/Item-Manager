package org.e_commerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.e_commerce.dto.ItemRequestDTO;
import org.e_commerce.dto.ItemResponseDTO;
import org.e_commerce.service.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService service;

    @Autowired
    private ObjectMapper objectMapper;

    // ---------- VALID INPUT ----------
    @Test
    void createItem_validInput_shouldReturn200() throws Exception {

        ItemRequestDTO dto =
                new ItemRequestDTO(1, "Laptop", "Gaming Laptop");

        Mockito.when(service.addItem(Mockito.any()))
                .thenReturn(new ItemResponseDTO(1, "Laptop", "Gaming Laptop"));

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    // Test1
    void createItem_invalidName_shouldReturn400() throws Exception {

        String json = """
        {
          "id": 1,
          "name": "Laptop123",
          "description": "Gaming Laptop"
        }
        """;

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }


    @Test
    void createItem_invalidId_shouldReturn400() throws Exception {

        String json = """
        {
          "id": 0,
          "name": "Laptop",
          "description": "Gaming Laptop"
        }
        """;

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }


    @Test
    void createItem_smallDescription_shouldReturn400() throws Exception {

        String json = """
        {
          "id": 1,
          "name": "Laptop",
          "description": "ok"
        }
        """;

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }


    @Test
    void createItem_largeDescription_shouldReturn400() throws Exception {

        String longDesc = "a".repeat(101);

        String json = """
        {
          "id": 1,
          "name": "Laptop",
          "description": "%s"
        }
        """.formatted(longDesc);

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
