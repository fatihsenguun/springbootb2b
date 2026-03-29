package com.fatihsengun.dto;

import com.fatihsengun.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoRegister {


    @NotBlank
    public String fullName;

    @NotNull
    public Role role;

    @NotBlank
    @Email
    public String email;

    @NotBlank
    public String password;

}
