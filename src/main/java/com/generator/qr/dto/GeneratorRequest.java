package com.generator.qr.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GeneratorRequest {
    @NotBlank(message = "Link cannot be blank")
    private String link;
}
