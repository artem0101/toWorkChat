package pnz.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.SequenceGenerator;

import lombok.Getter;

@Getter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @SequenceGenerator(name = "product_seq",
            sequenceName = "product_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @Column(name = "id")
    private Long id;

    // Название.
    @Column(name = "name")
    private String name;

    // Текущее количество.
    @Column(name = "amount_of_products")
    private Integer amountOfProduct;

    // Единица измерения.
    @Column(name = "unit_type")
    @Enumerated(EnumType.STRING)
    private ProductUnitType unitType;

    // Тип продукта.
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private Product() {}

    @Override
    public String toString() {
        return "Product[" + id + ", " +
                name + ", " + amountOfProduct + ", " + unitType + ", " + productType;
    }

    public static class ProductBuilder {
        private final Product newProduct;

        public ProductBuilder() {
            this.newProduct = new Product();
        }

        public ProductBuilder withName(String name) {
            newProduct.name = name;
            return this;
        }

        public ProductBuilder withAmountOfProduct(Integer amountOfProduct) {
            newProduct.amountOfProduct = amountOfProduct;
            return this;
        }

        public ProductBuilder withUnitType(ProductUnitType unitType) {
            newProduct.unitType = unitType;
            return this;
        }

        public ProductBuilder withProductType(ProductType productType) {
            newProduct.productType = productType;
            return this;
        }

        public Product build() {
            return newProduct;
        }
    }

}
