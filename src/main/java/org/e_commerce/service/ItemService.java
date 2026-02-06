package org.e_commerce.service;

import org.e_commerce.dto.ItemRequestDTO;
import org.e_commerce.model.Item;
import org.e_commerce.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repo;

    public Item addItem(ItemRequestDTO dto) {
        Item item = new Item(dto.getId(), dto.getName(), dto.getDescription());
        return repo.save(item);
    }

    public List<Item> getAllItems() {
        return repo.findAll();
    }

    public Item getItemById(int id) {
        return repo.findById(id);
    }

    public Item updateItem(int id, ItemRequestDTO dto) {
        Item item = new Item(dto.getId(), dto.getName(), dto.getDescription());
        return repo.update(id, item);
    }

    public void deleteItem(int id) {
        repo.delete(id);
    }
}