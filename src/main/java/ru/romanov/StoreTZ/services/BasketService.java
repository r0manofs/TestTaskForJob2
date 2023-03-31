package ru.romanov.StoreTZ.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.StoreTZ.entities.BasketEntity;
import ru.romanov.StoreTZ.entities.UserEntity;
import ru.romanov.StoreTZ.repositories.BasketRepository;
import ru.romanov.StoreTZ.util.EntityNotFoundException;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BasketService {
    private final BasketRepository basketRepository;

    @Autowired
    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public List<BasketEntity> findAll() {
        return basketRepository.findAll();
    }

    public BasketEntity findOne(int id) {
        Optional<BasketEntity> foundedProducts = basketRepository.findById(id);
        return foundedProducts.orElseThrow(EntityNotFoundException::new);
    }

    public @NotEmpty(message = "Empty user name")
    @Size(min = 2, max = 100, message = " Name should be between 2 and 100 characters")
    String findUsersByUserName(UserEntity user) {
        return user.getUserName();
        //TODO Не знаю че это за хуета, но надо разобраться как из баскета найти человеков
    }

    private void enrichBasket(BasketEntity basket) {
        basket.setProductId(basket.getProductId());
    }
}
