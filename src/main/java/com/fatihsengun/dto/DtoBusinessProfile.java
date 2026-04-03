package com.fatihsengun.dto;

import com.fatihsengun.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoBusinessProfile {

    private String companyName;

    private String bio;

    private String logoUrl;

    private String website;

    private DtoUser user;

    private Double averageRating = 0.0;
}
