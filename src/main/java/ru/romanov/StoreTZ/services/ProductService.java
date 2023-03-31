package ru.romanov.StoreTZ.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.StoreTZ.entities.ProductEntity;
import ru.romanov.StoreTZ.repositories.ProductRepository;
import ru.romanov.StoreTZ.util.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public ProductEntity findOne(int id) {
        Optional<ProductEntity> foundedProducts = productRepository.findById(id);
        return foundedProducts.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void save(ProductEntity product) {
        enrichProduct(product);

        productRepository.save(product);
    }

    @Transactional
    public void update(int id, ProductEntity updatedProduct) {
        updatedProduct.setProductId(id);
        productRepository.save(updatedProduct);
    }

    @Transactional
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    private void enrichProduct(ProductEntity product) {
        product.setProductId(product.getProductId());
    }
}
