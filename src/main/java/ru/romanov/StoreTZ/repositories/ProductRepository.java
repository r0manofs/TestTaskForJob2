package ru.romanov.StoreTZ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.romanov.StoreTZ.entities.ProductEntity;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
