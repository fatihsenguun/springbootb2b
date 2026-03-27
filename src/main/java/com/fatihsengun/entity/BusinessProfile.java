package com.fatihsengun.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "business_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessProfile extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String companyName;

    @Column(unique = true, nullable = false)
    private String taxNumber;

    @Column(length = 1000)
    private String bio;

    private String logoUrl;

    private String website;

    @Builder.Default
    private Double averageRating = 0.0;

}
