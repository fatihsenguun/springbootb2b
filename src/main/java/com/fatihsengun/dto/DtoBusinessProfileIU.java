package com.fatihsengun.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoBusinessProfileIU {

    @NotBlank(message = "Company name cannot be empty")
    private String companyName;

    @NotBlank(message = "Tax number is required for B2B verification")
    private String taxNumber;

    @Size(max = 1000, message = "Bio cannot exceed 1000 characters")
    private String bio;

    private String logoUrl;

    private String website;

}
