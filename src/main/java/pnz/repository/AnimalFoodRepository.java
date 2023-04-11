package pnz.repository;

import javax.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pnz.dto.CalculationRequirementFoodsDto;
import pnz.entity.AnimalFoodIntake;

@Transactional
@Repository
public interface AnimalFoodRepository extends JpaRepository<AnimalFoodIntake, Long>, CrudRepository<AnimalFoodIntake, Long> {

    @Query(name = "calcProductNeeds", nativeQuery = true)
    List<CalculationRequirementFoodsDto> calcProductNeeds();

    @Modifying
    @Query("UPDATE AnimalFoodIntake a SET a.compositionRate = :productRate WHERE a.animalId = :animalId AND a.productId = :productId")
    void updateConsumptionRate(Long animalId, Long productRate, Long productId);

}
