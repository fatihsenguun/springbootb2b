package com.fatihsengun.dto;

import com.fatihsengun.entity.BusinessProfile;
import com.fatihsengun.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoUser {

    private String email;


    private String fullName;

    private Role role;

}
