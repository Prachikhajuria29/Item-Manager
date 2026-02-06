package org.e_commerce.Mapper;

import org.e_commerce.dto.ItemRequestDTO;
import org.e_commerce.dto.ItemResponseDTO;
import org.e_commerce.model.Item;

public class ItemMapper {

    // DTO → Model
    public static Item toModel(ItemRequestDTO dto) {
        return new Item(
                dto.id(),
                dto.name(),
                dto.description()
        );
    }

    // Model → Response DTO
    public static ItemResponseDTO toResponse(Item item) {
        return new ItemResponseDTO(
                item.getId(),
                item.getName(),
                item.getDescription()
        );
    }
}
