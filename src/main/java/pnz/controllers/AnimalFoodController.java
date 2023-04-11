package pnz.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import pnz.dto.CalculateProductByWeekDto;
import pnz.dto.ProductByAnimalDto;
import pnz.entity.AnimalFoodIntake;
import pnz.repository.AnimalFoodRepository;
import pnz.repository.AnimalRepository;
import pnz.repository.ProductsRepository;

@Slf4j
@RestController
public class AnimalFoodController {

    private final AnimalFoodRepository animalFoodRepository;
    private final AnimalRepository animalRepository;
    private final ProductsRepository productsRepository;

    public AnimalFoodController(AnimalFoodRepository animalFoodRepository,
                                AnimalRepository animalRepository,
                                ProductsRepository productsRepository) {
        this.animalFoodRepository = animalFoodRepository;
        this.animalRepository = animalRepository;
        this.productsRepository = productsRepository;
    }

    @GetMapping("animalfood/by-week")
    public @ResponseBody CalculateProductByWeekDto calculationRequirementFoods(
            @RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate) {
        var productInfos = animalFoodRepository.calcProductNeeds();
        var endDateTime = new Date(startDate.getTime() + TimeUnit.DAYS.toMillis(7));
        return new CalculateProductByWeekDto(
                startDate,
                endDateTime,
                productInfos
        );
    }

    @Modifying
    @PutMapping("/animalfood/update-product-ration")
    public void updateConsumptionRate(
            @RequestParam("animalId") Long animalId,
            @RequestParam("productRate") Long productRate,
            @RequestParam("productId") Long productId) {
        animalFoodRepository.updateConsumptionRate(animalId, productRate, productId);
    }

    @PostMapping("/animalfood/addProduct-to-animal")
    public void addProductToAnimal(@RequestBody ProductByAnimalDto productByAnimalDto) {
        var animalName = productByAnimalDto.getAnimalName();
        var animalId = Optional.ofNullable(animalRepository.findIdByName(animalName));
        if (animalId.isEmpty()) {
            return;
        }
        var productId = Optional.ofNullable(productsRepository.findIdByName(productByAnimalDto.getProductName()));
        if (productId.isEmpty()) {
            return;
        }
        var productInfo = new AnimalFoodIntake.AnimalFoodIntakeBuilder().withProductId(productId.get())
                .withAnimalId(animalId.get())
                .withCompositionRate(productByAnimalDto.getProductCount())
                .build();
        animalFoodRepository.save(productInfo);
    }

    @PostMapping("/animalfood/addProducts-to-animal")
    public void addProductsToAnimal(@RequestBody List<ProductByAnimalDto> productByAnimalDtos) {
        productByAnimalDtos.forEach(this::addProductToAnimal);
    }

}
