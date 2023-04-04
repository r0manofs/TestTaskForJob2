package ru.romanov.StoreTZ.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "user_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "user_name")
    @NotEmpty(message = "Empty user name")
    @Size(min = 2, max = 100, message = " Name should be between 2 and 100 characters")
    private String userName;
    @Column(name = "user_email")
    @NotEmpty(message = "Empty email")
    @Size(min = 2, max = 100, message = "Email should be between 2 and 100 characters")
    @Email
    private String userEmail;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @ManyToMany
    @JoinTable(name = "basket",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductEntity> products;

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", balance=" + balance +
                '}';
    }
}
