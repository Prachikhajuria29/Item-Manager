package org.e_commerce.dto;

import jakarta.validation.constraints.*;

public class ItemRequestDTO {

    @NotNull(message = "Id is required")
    @Min(value = 1, message = "Id must be positive")
    @Max(value = 999999, message = "Id too large")
    private Integer id;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[A-Za-z ]+$",
            message = "Name must contain only letters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 3, max = 100,
            message = "Description length must be 3–100")
    private String description;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
