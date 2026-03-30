package com.fatihsengun.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken extends  BaseEntity {

    @Column(nullable = false, unique = true)
    private String refreshToken;

    @Column(name = "expire_date")
    private LocalDateTime expireDate;

    @ManyToOne
    private User user;

}

