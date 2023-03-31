package ru.romanov.StoreTZ.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketDTO {
    private int productId;
    private int userId;
    private int quantiy;
}
