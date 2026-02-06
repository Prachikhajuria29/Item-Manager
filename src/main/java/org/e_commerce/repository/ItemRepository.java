package org.e_commerce.repository;

import org.e_commerce.model.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepository {

    private final List<Item> items = new ArrayList<>();

    public Item save(Item item) {
        items.add(item);
        return item;
    }

    public List<Item> findAll() {
        return items;
    }

    public Item findById(int id) {
        return items.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void delete(int id) {
        items.removeIf(i -> i.getId() == id);
    }

    public Item update(int id, Item newItem) {
        Item old = findById(id);
        if (old != null) {
            old.setName(newItem.getName());
            old.setDescription(newItem.getDescription());
        }
        return old;
    }
}
