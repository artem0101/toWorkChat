package pnz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pnz.entity.*;
import pnz.repository.AnimalFoodRepository;
import pnz.repository.AnimalRepository;
import pnz.repository.ProductsRepository;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(
            ProductsRepository productsRepository,
            AnimalRepository animalRepository,
            AnimalFoodRepository animalFoodRepository) {
        return args -> {
            animalFoodRepository.deleteAll();
            animalRepository.deleteAll();
            productsRepository.deleteAll();

            var carrot = new Product.ProductBuilder().withName("Морковь")
                    .withAmountOfProduct(44)
                    .withUnitType(ProductUnitType.QUANTITY)
                    .withProductType(ProductType.VEGETABLE)
                    .build();
            var meat = new Product.ProductBuilder().withName("Мясо")
                    .withAmountOfProduct(144)
                    .withUnitType(ProductUnitType.KILOGRAM)
                    .withProductType(ProductType.MEAT)
                    .build();
            var cabbage = new Product.ProductBuilder().withName("Капуста")
                    .withAmountOfProduct(32)
                    .withUnitType(ProductUnitType.QUANTITY)
                    .withProductType(ProductType.VEGETABLE)
                    .build();
            var banana = new Product.ProductBuilder().withName("Банан")
                    .withAmountOfProduct(23)
                    .withUnitType(ProductUnitType.QUANTITY)
                    .withProductType(ProductType.FRUIT)
                    .build();
            var apple = new Product.ProductBuilder().withName("Яблоко")
                    .withAmountOfProduct(0)
                    .withUnitType(ProductUnitType.QUANTITY)
                    .withProductType(ProductType.FRUIT)
                    .build();
            var corn = new Product.ProductBuilder().withName("Зерно")
                    .withAmountOfProduct(11)
                    .withUnitType(ProductUnitType.KILOGRAM)
                    .withProductType(ProductType.CEREALS)
                    .build();
            var water = new Product.ProductBuilder().withName("Вода")
                    .withAmountOfProduct(11)
                    .withUnitType(ProductUnitType.LITER)
                    .withProductType(ProductType.WATER)
                    .build();
            productsRepository.save(carrot);
            productsRepository.save(meat);
            productsRepository.save(cabbage);
            productsRepository.save(banana);
            productsRepository.save(apple);
            productsRepository.save(corn);
            productsRepository.save(water);

            var monkey = new Animal.AnimalBuilder().withName("Обезьяна")
                    .withType(AnimalType.MAMMAL)
                    .withIsPredator(false)
                    .build();
            var rabbit = new Animal.AnimalBuilder().withName("Заяц")
                    .withType(AnimalType.MAMMAL)
                    .withIsPredator(false)
                    .build();
            var eagle = new Animal.AnimalBuilder().withName("Орёл")
                    .withType(AnimalType.BIRD)
                    .withIsPredator(true)
                    .build();
            var tiger = new Animal.AnimalBuilder().withName("Тигр")
                    .withType(AnimalType.MAMMAL)
                    .withIsPredator(true)
                    .build();
            var horse = new Animal.AnimalBuilder().withName("Лошадь")
                    .withType(AnimalType.MAMMAL)
                    .withIsPredator(false)
                    .build();
            animalRepository.save(monkey);
            animalRepository.save(rabbit);
            animalRepository.save(eagle);
            animalRepository.save(tiger);
            animalRepository.save(horse);

            var monkeyWater = new AnimalFoodIntake.AnimalFoodIntakeBuilder()
                    .withAnimalId(monkey.getId())
                    .withProductId(water.getId())
                    .withCompositionRate(3L)
                    .build();
            var monkeBanana = new AnimalFoodIntake.AnimalFoodIntakeBuilder()
                    .withAnimalId(monkey.getId())
                    .withProductId(banana.getId())
                    .withCompositionRate(5L)
                    .build();
            var monkeApple = new AnimalFoodIntake.AnimalFoodIntakeBuilder()
                    .withAnimalId(monkey.getId())
                    .withProductId(apple.getId())
                    .withCompositionRate(4L)
                    .build();

            var rabbitWater = new AnimalFoodIntake.AnimalFoodIntakeBuilder()
                    .withAnimalId(rabbit.getId())
                    .withProductId(water.getId())
                    .withCompositionRate(1L)
                    .build();
            var rabbitCarrot = new AnimalFoodIntake.AnimalFoodIntakeBuilder()
                    .withAnimalId(rabbit.getId())
                    .withProductId(carrot.getId())
                    .withCompositionRate(3L)
                    .build();
            var rabbitCabbage = new AnimalFoodIntake.AnimalFoodIntakeBuilder()
                    .withAnimalId(rabbit.getId())
                    .withProductId(cabbage.getId())
                    .withCompositionRate(2L)
                    .build();

            var eagleWater = new AnimalFoodIntake.AnimalFoodIntakeBuilder()
                    .withAnimalId(eagle.getId())
                    .withProductId(water.getId())
                    .withCompositionRate(1L)
                    .build();
            var eagleMeat = new AnimalFoodIntake.AnimalFoodIntakeBuilder()
                    .withAnimalId(eagle.getId())
                    .withProductId(meat.getId())
                    .withCompositionRate(3L)
                    .build();
            var eagleCorn = new AnimalFoodIntake.AnimalFoodIntakeBuilder()
                    .withAnimalId(eagle.getId())
                    .withProductId(corn.getId())
                    .withCompositionRate(1L)
                    .build();
            var eagleApple = new AnimalFoodIntake.AnimalFoodIntakeBuilder()
                    .withAnimalId(eagle.getId())
                    .withProductId(apple.getId())
                    .withCompositionRate(2L)
                    .build();


            var tigerWater = new AnimalFoodIntake.AnimalFoodIntakeBuilder()
                    .withAnimalId(tiger.getId())
                    .withProductId(water.getId())
                    .withCompositionRate(10L)
                    .build();
            var tigerMeat = new AnimalFoodIntake.AnimalFoodIntakeBuilder()
                    .withAnimalId(tiger.getId())
                    .withProductId(water.getId())
                    .withCompositionRate(7L)
                    .build();

            var horseWater = new AnimalFoodIntake.AnimalFoodIntakeBuilder()
                    .withAnimalId(horse.getId())
                    .withProductId(water.getId())
                    .withCompositionRate(11L)
                    .build();
            var horseCorn = new AnimalFoodIntake.AnimalFoodIntakeBuilder()
                    .withAnimalId(horse.getId())
                    .withProductId(corn.getId())
                    .withCompositionRate(7L)
                    .build();
            var horseApple = new AnimalFoodIntake.AnimalFoodIntakeBuilder()
                    .withAnimalId(horse.getId())
                    .withProductId(apple.getId())
                    .withCompositionRate(6L)
                    .build();
            var horseCarrot = new AnimalFoodIntake.AnimalFoodIntakeBuilder()
                    .withAnimalId(horse.getId())
                    .withProductId(carrot.getId())
                    .withCompositionRate(5L)
                    .build();

            animalFoodRepository.save(monkeyWater);
            animalFoodRepository.save(monkeBanana);
            animalFoodRepository.save(monkeApple);
            animalFoodRepository.save(rabbitWater);
            animalFoodRepository.save(rabbitCarrot);
            animalFoodRepository.save(rabbitCabbage);
            animalFoodRepository.save(eagleWater);
            animalFoodRepository.save(eagleMeat);
            animalFoodRepository.save(eagleCorn);
            animalFoodRepository.save(eagleApple);
            animalFoodRepository.save(tigerWater);
            animalFoodRepository.save(tigerMeat);
            animalFoodRepository.save(horseWater);
            animalFoodRepository.save(horseCorn);
            animalFoodRepository.save(horseApple);
            animalFoodRepository.save(horseCarrot);
        };
    }

}
