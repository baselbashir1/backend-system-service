package com.task.backendsystem.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record BundleRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name should be less than 100 characters")
        String name,
        @Size(max = 300, message = "Description should be less than 300 characters")
        String description,
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        BigDecimal price
) {
}