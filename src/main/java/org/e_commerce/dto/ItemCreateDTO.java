package org.e_commerce.dto;

import jakarta.validation.constraints.*;

public record ItemCreateDTO(

        @NotNull(message = "Id is required")
        @Min(value = 1, message = "Id must be positive")
        Integer id,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description
) {}
