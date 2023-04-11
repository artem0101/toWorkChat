package pnz.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pnz.entity.Animal;

@Transactional
@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {

    @Modifying
    @Query("UPDATE AnimalFoodIntake a SET a.compositionRate = :productRate WHERE a.animalId = :animalId AND a.productId = :productId")
    void updateConsumptionRate(Long animalId, Long productRate, Long productId);

    @Query("SELECT a.id FROM Animal a WHERE a.name = :animalName")
    Long findIdByName(String animalName);

}
