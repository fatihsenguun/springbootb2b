package com.fatihsengun.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "business_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessProfile extends BaseEntity{
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
