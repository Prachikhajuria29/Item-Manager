package org.e_commerce.repository;

import org.e_commerce.model.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepository {

    private final List<Item> items = new ArrayList<>();

    // SAVE
    public Item save(Item item) {
        items.add(item);
        return item;
    }

    // FIND ALL
    public List<Item> findAll() {
        return items;
    }

    // FIND BY ID
    public Item findById(int id) {
        return items.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // DELETE
    public void delete(int id) {
        items.removeIf(i -> i.getId() == id);
    }

    // UPDATE
    public Item update(int id, Item newItem) {
        Item old = findById(id);
        if (old != null) {
            old.setName(newItem.getName());
            old.setDescription(newItem.getDescription());
        }
        return old;
    }

    // AUTO GENERATE ID
    public int getNextId() {
        return items.stream()
                .mapToInt(Item::getId)
                .max()
                .orElse(0) + 1;
    }

    // CLEAR ALL ITEMS (for testing)
    public void clear() {
        items.clear();
    }

    // SEARCH ITEMS by name or description using pattern
    public List<Item> search(String pattern) {
        if (pattern == null || pattern.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return items.stream()
                .filter(item -> matchesPattern(item, pattern))
                .toList();
    }

    /**
     * Checks if an item matches the search pattern
     * Searches in both name and description fields
     */
    private boolean matchesPattern(Item item, String pattern) {
        // Search in name
        if (item.getName() != null &&
            org.e_commerce.util.KMPSearch.contains(item.getName(), pattern)) {
            return true;
        }

        // Search in description
        if (item.getDescription() != null &&
            org.e_commerce.util.KMPSearch.contains(item.getDescription(), pattern)) {
            return true;
        }

        return false;
    }
}
