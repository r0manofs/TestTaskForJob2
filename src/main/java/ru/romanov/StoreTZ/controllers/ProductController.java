package ru.romanov.StoreTZ.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.romanov.StoreTZ.dto.ProductDTO;
import ru.romanov.StoreTZ.entities.ProductEntity;
import ru.romanov.StoreTZ.services.ProductService;
import ru.romanov.StoreTZ.util.EntityErrorResponse;
import ru.romanov.StoreTZ.util.EntityNotCreatedException;
import ru.romanov.StoreTZ.util.EntityNotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<ProductDTO> getProducts() {
        return productService.findAll().stream().map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable("id") int id) {
        return convertToProductDTO(productService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ProductDTO productDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors)
                errorMessage.append(error.getField())
                        .append("-")
                        .append(error.getDefaultMessage())
                        .append(";");

            throw new EntityNotCreatedException(errorMessage.toString());
        }
        productService.save(convertToProduct(productDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid ProductDTO productDTO,
                                             @PathVariable("id") int id,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append("-")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new EntityNotCreatedException(errorMessage.toString());
        }
        productService.update(id,convertToProduct(productDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        productService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<EntityErrorResponse> handleException(EntityNotFoundException e) {
        EntityErrorResponse response = new EntityErrorResponse(
                e + " Product with this Id not found", System.currentTimeMillis()
        );

        //404
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<EntityErrorResponse> handleException(EntityNotCreatedException e) {
        EntityErrorResponse response = new EntityErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );

        //404
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private ProductEntity convertToProduct(ProductDTO productDTO) {

        return modelMapper.map(productDTO, ProductEntity.class);
    }
    private ProductDTO convertToProductDTO(ProductEntity product) {
        return modelMapper.map(product, ProductDTO.class);
    }
}
