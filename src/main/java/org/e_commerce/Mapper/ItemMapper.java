package org.e_commerce.Mapper;

import org.e_commerce.dto.ItemCreateDTO;
import org.e_commerce.dto.ItemUpdateDTO;
import org.e_commerce.dto.ItemResponseDTO;
import org.e_commerce.model.Item;

public class ItemMapper {

    // CREATE → DTO to Model
    public static Item toModel(ItemCreateDTO dto) {
        return new Item(
                dto.id(),        // ID comes from body during create
                dto.name(),
                dto.description()
        );
    }

    // UPDATE → DTO to Model (ID comes from PATH)
    public static Item toModel(ItemUpdateDTO dto, int id) {
        return new Item(
                id,              // ID comes from URL path
                dto.name(),
                dto.description()
        );
    }

    // MODEL → RESPONSE DTO
    public static ItemResponseDTO toResponse(Item item) {
        return new ItemResponseDTO(
                item.getId(),
                item.getName(),
                item.getDescription()
        );
    }
}
