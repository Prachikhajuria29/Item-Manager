package org.e_commerce.dto;

import jakarta.validation.constraints.*;

public record ItemUpdateDTO(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description
) {}
