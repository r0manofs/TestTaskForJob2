package ru.romanov.StoreTZ.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @NotEmpty(message = "Empty product name")
    @Size(min = 1, max = 100, message ="Product name should be between 1 and 100 characters")
    private String ProductName;
    @Size(max = 100, message ="Product description can't be larger than 100 characters")
    private String productDescription;
    private BigDecimal productPrice;
    private String productKeyWord;
    private int productScore;
}
