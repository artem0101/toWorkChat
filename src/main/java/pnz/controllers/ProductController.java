package pnz.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import pnz.entity.Product;
import pnz.repository.ProductsRepository;

@Slf4j
@RestController
public class ProductController {

    private final ProductsRepository productsRepo;

    public ProductController(ProductsRepository productsRepo) {
        this.productsRepo = productsRepo;
    }

    @PostMapping("/product")
    public void saveProduct(@RequestBody Product product) {
        productsRepo.save(product);
    }

    @GetMapping("/product")
    public @ResponseBody List<Product> getAllProducts() {
        var iterable = productsRepo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/product/{id}")
    public @ResponseBody Optional<Product> getProductById(@PathVariable Long id) {
        return productsRepo.findById(id);
    }

    @DeleteMapping("/product/remove-all")
    public void removeAllProducts() {
        productsRepo.deleteAll();
    }

    @DeleteMapping("/product/remove/{id}")
    public void deleteProductById(@PathVariable Long id) {
        try {
            productsRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.warn("Удаляемого идентификатора " + id + " нет в системе.");
        }
    }

    @DeleteMapping("/product/remove-by-ids")
    public void deleteProductsByIds(@RequestParam("ids") List<Long> ids) {
        try {
            productsRepo.deleteAllById(ids);
        } catch (EmptyResultDataAccessException e) {
            log.warn("Один или несколько из удаляемых параметров " + ids.toString() + " отстутствует в системе");
        }
    }

    @Modifying
    @PutMapping("/product/update-product-count")
    public void updateConsumptionRate(
            @RequestParam("amountOfProduct") Integer amountOfProduct,
            @RequestParam("productId") Long productId) {
        productsRepo.updateProductsCount(amountOfProduct, productId);
    }

}
