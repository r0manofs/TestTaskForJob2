package ru.romanov.StoreTZ.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "basket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "quantity")
    private int quantiy;
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private ProductEntity product;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity user;
}
