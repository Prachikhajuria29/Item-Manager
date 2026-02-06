package org.e_commerce.controller;

import jakarta.validation.Valid;
import org.e_commerce.dto.ItemRequestDTO;
import org.e_commerce.model.Item;
import org.e_commerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService service;

    // CREATE
    @PostMapping
    public Item addItem(@Valid @RequestBody ItemRequestDTO dto) {
        return service.addItem(dto);
    }

    // READ ALL
    @GetMapping
    public List<Item> getAllItems() {
        return service.getAllItems();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Item getItem(@PathVariable int id) {
        return service.getItemById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Item updateItem(@PathVariable int id,
                           @Valid @RequestBody ItemRequestDTO dto) {
        return service.updateItem(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable int id) {
        service.deleteItem(id);
        return "Item deleted successfully";
    }
}
