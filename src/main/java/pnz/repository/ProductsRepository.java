package pnz.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import pnz.entity.Product;

@Transactional
@Repository
public interface ProductsRepository extends CrudRepository<Product, Long> {

    @Modifying
    @Query("UPDATE Product a SET a.amountOfProduct = :amountOfProduct WHERE a.id = :productId")
    void updateProductsCount(Integer amountOfProduct, Long productId);

    @Query("SELECT a.id FROM Product a WHERE a.name = :productName")
    Long findIdByName(String productName);

}
