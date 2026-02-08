package org.e_commerce.service;

import org.e_commerce.Exception.DuplicateItemException;
import org.e_commerce.Exception.ItemNotFoundException;
import org.e_commerce.Mapper.ItemMapper;
import org.e_commerce.dto.ItemCreateDTO;
import org.e_commerce.dto.ItemResponseDTO;
import org.e_commerce.dto.ItemUpdateDTO;
import org.e_commerce.model.Item;
import org.e_commerce.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository repo;

    public ItemService(ItemRepository repo) {
        this.repo = repo;
    }

    // CREATE  (ID in body OR auto — your choice)
    public ItemResponseDTO addItem(ItemCreateDTO dto) {

        // if you want ID from body
        if (repo.findById(dto.id()) != null) {
            throw new DuplicateItemException(
                    "Item with id " + dto.id() + " already exists"
            );
        }

        Item item = ItemMapper.toModel(dto);
        Item saved = repo.save(item);

        return ItemMapper.toResponse(saved);
    }

    // READ ALL
    public List<ItemResponseDTO> getAllItems() {
        return repo.findAll()
                .stream()
                .map(ItemMapper::toResponse)
                .collect(Collectors.toList());
    }

    // READ BY ID
    public ItemResponseDTO getItemById(int id) {
        Item item = repo.findById(id);

        if (item == null) {
            throw new ItemNotFoundException(
                    "Item with id " + id + " not found"
            );
        }

        return ItemMapper.toResponse(item);
    }

    // UPDATE  (ID ONLY FROM PATH)
    public ItemResponseDTO updateItem(int id, ItemUpdateDTO dto) {

        Item existing = repo.findById(id);

        if (existing == null) {
            throw new ItemNotFoundException(
                    "Item with id " + id + " not found"
            );
        }

        existing.setName(dto.name());
        existing.setDescription(dto.description());

        Item updated = repo.update(id, existing);

        return ItemMapper.toResponse(updated);
    }

    // DELETE
    public void deleteItem(int id) {

        Item existing = repo.findById(id);

        if (existing == null) {
            throw new ItemNotFoundException(
                    "Item with id " + id + " not found"
            );
        }

        repo.delete(id);
    }

    // SEARCH
    public List<ItemResponseDTO> searchItems(String query) {
        List<Item> results = repo.search(query);

        return results.stream()
                .map(ItemMapper::toResponse)
                .collect(Collectors.toList());
    }
}
