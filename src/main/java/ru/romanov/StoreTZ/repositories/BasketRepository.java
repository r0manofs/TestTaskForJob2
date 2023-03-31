package ru.romanov.StoreTZ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.romanov.StoreTZ.entities.BasketEntity;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity, Integer> {
//    public List<BasketEntity> findByUser(UserEntity userEntity);

}
