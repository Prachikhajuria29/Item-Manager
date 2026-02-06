package org.e_commerce.dto;

import jakarta.validation.constraints.*;

public record ItemRequestDTO(

        @NotNull(message = "Id is required")
        @Min(value = 1, message = "Id must be positive")
        @Max(value = 999999, message = "Id too large")
        Integer id,

        @NotBlank(message = "Name is required")
        @Pattern(regexp = "^[A-Za-z ]+$",
                message = "Name must contain only letters")
        String name,

        @NotBlank(message = "Description is required")
        @Size(min = 3, max = 100,
                message = "Description length must be 3–100")
        String description

) {}
