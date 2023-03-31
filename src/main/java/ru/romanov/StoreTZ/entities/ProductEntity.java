package ru.romanov.StoreTZ.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Column(name = "product_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(name = "product_name")
    @NotEmpty(message = "Empty product name")
    @Size(min = 1, max = 100, message = "Product name should be between 1 and 100 characters")
    private String ProductName;
    @Column(name = "product_description")
    @Size(max = 100, message = "Product description can't be larger than 100 characters")
    private String productDescription;
    @Column(name = "product_price")
    private BigDecimal productPrice;
    @Column(name = "product_keyword")
    private String productKeyWord;
    @Column(name = "product_score")
    private int productScore;
    @ManyToMany(mappedBy = "products")
    List<UserEntity> users;
}
