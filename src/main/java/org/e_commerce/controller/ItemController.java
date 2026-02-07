package org.e_commerce.controller;

import jakarta.validation.Valid;
import org.e_commerce.dto.ItemCreateDTO;
import org.e_commerce.dto.ItemUpdateDTO;
import org.e_commerce.dto.ItemResponseDTO;
import org.e_commerce.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService service;

    // Constructor Injection
    public ItemController(ItemService service) {
        this.service = service;
    }

    // CREATE → ID in BODY
    @PostMapping
    public ResponseEntity<ItemResponseDTO> addItem(
            @Valid @RequestBody ItemCreateDTO dto) {

        return new ResponseEntity<>(
                service.addItem(dto),
                HttpStatus.CREATED
        );
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getAllItems() {
        return ResponseEntity.ok(service.getAllItems());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> getItem(@PathVariable int id) {
        return ResponseEntity.ok(service.getItemById(id));
    }

    // UPDATE → ID in PATH ONLY
    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> updateItem(
            @PathVariable int id,
            @Valid @RequestBody ItemUpdateDTO dto) {

        return ResponseEntity.ok(service.updateItem(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
