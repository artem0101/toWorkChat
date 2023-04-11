package pnz.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.ConstructorResult;
import javax.persistence.ColumnResult;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

import lombok.Data;
import pnz.dto.CalculationRequirementFoodsDto;

@Data
@Entity
@Table(name = "animal_food")
@NamedNativeQuery(
        name = "calcProductNeeds",
        query = "SELECT\n" +
                "a.name,\n" +
                "a.amount_of_products * 7 AS actualProductCount,\n" +
                "b.expectedProductCount * 7 AS expectedProductCount,\n" +
                "CASE\n" +
                "\tWHEN\n" +
                "    \t((b.expectedProductCount * 7) - (a.amount_of_products * 7)) > 0 THEN ((b.expectedProductCount * 7) - (a.amount_of_products * 7))\n" +
                "\tELSE NULL END\n" +
                "\t\tAS neededProductCount\n" +
                "FROM zoo.product AS a\n" +
                "INNER JOIN\n" +
                "(SELECT SUM(composition_rate) AS expectedProductCount, product_id  FROM zoo.animal_food GROUP BY  product_id) AS b\n" +
                "ON a.id = b.product_id;",
        resultSetMapping = "calculated_requirement_foods_dto"
)
@SqlResultSetMapping(
        name = "calculated_requirement_foods_dto",
        classes = @ConstructorResult(
                targetClass = CalculationRequirementFoodsDto.class,
                columns = {
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "actualProductCount", type = Long.class),
                        @ColumnResult(name = "expectedProductCount", type = Long.class),
                        @ColumnResult(name = "neededProductCount", type = Long.class)
                }
        )
)
public class AnimalFoodIntake {

    @Id
    @SequenceGenerator(name = "animal_food_seq",
            sequenceName = "animal_food_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_food_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "animal_id")
    private Long animalId;

    @Column(name = "product_id")
    private Long productId;

    // Норма потребления.
    @Column(name = "composition_rate")
    private Long compositionRate;

    private AnimalFoodIntake() {}

    public static class AnimalFoodIntakeBuilder {
        private final AnimalFoodIntake newAnimalFoodIntake;

        public AnimalFoodIntakeBuilder() {
            newAnimalFoodIntake = new AnimalFoodIntake();
        }

        public AnimalFoodIntakeBuilder withAnimalId(Long animalId) {
            newAnimalFoodIntake.animalId = animalId;
            return this;
        }

        public AnimalFoodIntakeBuilder withProductId(Long productId) {
            newAnimalFoodIntake.productId = productId;
            return this;
        }

        public AnimalFoodIntakeBuilder withCompositionRate(Long count) {
            newAnimalFoodIntake.compositionRate = count;
            return this;
        }

        public AnimalFoodIntake build() {
            return newAnimalFoodIntake;
        }
    }

}
