package ru.romanov.StoreTZ.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotEmpty(message = "Empty user name")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String userName;
    @NotEmpty(message = "Empty email")
    @Size(min = 2, max = 100, message = "Email should be between 2 and 100 characters")
    @Email
    private String userEmail;
    @Column(name = "balance")
    private BigDecimal balance;
}
