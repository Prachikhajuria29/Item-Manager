package org.e_commerce.controller;

import org.e_commerce.dto.ItemCreateDTO;
import org.e_commerce.dto.ItemUpdateDTO;
import org.e_commerce.dto.ItemResponseDTO;
import org.e_commerce.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items-ui")
public class ItemViewController {

    private final ItemService itemService;

    public ItemViewController(ItemService itemService) {
        this.itemService = itemService;
    }

    // LIST
    @GetMapping
    public String listItems(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "items";
    }

    // ADD FORM
    @GetMapping("/add")
    public String showAddForm(Model model) {
        return "add-item";
    }

    // SAVE NEW ITEM
    @PostMapping("/save")
    public String saveItem(@ModelAttribute ItemCreateDTO item) {
        itemService.addItem(item);
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
                             @ModelAttribute ItemUpdateDTO item) {
        itemService.updateItem(id, item);
        return "redirect:/items-ui";
    }

    @GetMapping("/view/{id}")
    public String viewItem(@PathVariable int id, Model model) {
        ItemResponseDTO itemResponseDTO = itemService.getItemById(id);
        model.addAttribute("item", itemResponseDTO);
        return "view-item";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable int id) {
        itemService.deleteItem(id);
        return "redirect:/items-ui";
    }



}
