package org.e_commerce.service;

import org.e_commerce.dto.ItemRequestDTO;
import org.e_commerce.dto.ItemResponseDTO;
import org.e_commerce.model.Item;
import org.e_commerce.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repo;

    // CREATE
    public ItemResponseDTO addItem(ItemRequestDTO dto) {
        Item item = new Item(dto.id(), dto.name(), dto.description());
        Item saved = repo.save(item);

        return new ItemResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getDescription()
        );
    }

    // READ ALL
    public List<ItemResponseDTO> getAllItems() {
        return repo.findAll()
                .stream()
                .map(i -> new ItemResponseDTO(
                        i.getId(),
                        i.getName(),
                        i.getDescription()))
                .collect(Collectors.toList());
    }

    // READ BY ID
    public ItemResponseDTO getItemById(int id) {
        Item item = repo.findById(id);
        if (item == null) return null;

        return new ItemResponseDTO(
                item.getId(),
                item.getName(),
                item.getDescription()
        );
    }

    // UPDATE
    public ItemResponseDTO updateItem(int id, ItemRequestDTO dto) {
        Item item = new Item(dto.id(), dto.name(), dto.description());
        Item updated = repo.update(id, item);

        if (updated == null) return null;

        return new ItemResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getDescription()
        );
    }

    // DELETE
    public void deleteItem(int id) {
        repo.delete(id);
    }
}
