package org.e_commerce.controller;

import jakarta.validation.Valid;
import org.e_commerce.dto.ItemCreateDTO;
import org.e_commerce.dto.ItemUpdateDTO;
import org.e_commerce.dto.ItemResponseDTO;
import org.e_commerce.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/items-ui")
public class ItemViewController {

    private final ItemService itemService;

    public ItemViewController(ItemService itemService) {
        this.itemService = itemService;
    }

    // LIST & SEARCH
    @GetMapping
    public String listItems(@RequestParam(required = false) String search, Model model) {
        List<ItemResponseDTO> items = itemService.getAllItems();

        // Filter items if search query is provided
        if (search != null && !search.trim().isEmpty()) {
            String searchLower = search.trim().toLowerCase();
            items = items.stream()
                    .filter(item ->
                        item.name().toLowerCase().contains(searchLower) ||
                        item.description().toLowerCase().contains(searchLower) ||
                        String.valueOf(item.id()).contains(searchLower)
                    )
                    .collect(Collectors.toList());
            model.addAttribute("searchQuery", search);
            model.addAttribute("searchResultCount", items.size());
        }

        model.addAttribute("items", items);
        return "items";
    }

    // ADD FORM
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("item", new ItemCreateDTO(null, "", ""));
        return "add-item";
    }

    // SAVE NEW ITEM
    @PostMapping("/save")
    public String saveItem(@Valid @ModelAttribute("item") ItemCreateDTO item,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "add-item";
        }

        try {
            itemService.addItem(item);
            redirectAttributes.addFlashAttribute("message", "Item created successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed to create item: " + e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
        }

        return "redirect:/items-ui";
    }

    // EDIT FORM (GET BY ID)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        ItemResponseDTO item = itemService.getItemById(id);
        model.addAttribute("item", item);
        return "edit-item";
    }

    // UPDATE ITEM
    @PostMapping("/update/{id}")
    public String updateItem(@PathVariable int id,
                             @Valid @ModelAttribute("item") ItemUpdateDTO item,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if (bindingResult.hasErrors()) {
            ItemResponseDTO itemResponse = itemService.getItemById(id);
            model.addAttribute("item", itemResponse);
            return "edit-item";
        }

        try {
            itemService.updateItem(id, item);
            redirectAttributes.addFlashAttribute("message", "Item updated successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed to update item: " + e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
        }

        return "redirect:/items-ui";
    }

    @GetMapping("/view/{id}")
    public String viewItem(@PathVariable int id, Model model) {
        ItemResponseDTO itemResponseDTO = itemService.getItemById(id);
        model.addAttribute("item", itemResponseDTO);
        return "view-item";
    }

    // DELETE
    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            itemService.deleteItem(id);
            redirectAttributes.addFlashAttribute("message", "Item deleted successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed to delete item: " + e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/items-ui";
    }



}
