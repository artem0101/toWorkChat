package pnz.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import pnz.entity.Animal;
import pnz.repository.AnimalRepository;

@Slf4j
@RestController
public class AnimalController {

    private final AnimalRepository animalRepo;

    public AnimalController(AnimalRepository animalRepo) {
        this.animalRepo = animalRepo;
    }

    @PostMapping("/animal/add")
    public void addAnimal(@RequestBody Animal animal) {
        animalRepo.save(animal);
    }

    @GetMapping("/animal/get-all")
    public @ResponseBody List<Animal> getAllProducts() {
        var iterable = animalRepo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/animal/get/{id}")
    public @ResponseBody Optional<Animal> getProductById(@PathVariable Long id) {
        return animalRepo.findById(id);
    }

    @DeleteMapping("/animal/remove-all")
    public void removeAllAnimals() {
        animalRepo.deleteAll();
    }

    @DeleteMapping("/animal/remove/{id}")
    public void deleteAnimalById(@PathVariable Long id) {
        try {
            animalRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.warn("Удаляемого идентификатора " + id + " нет в системе.");
        }
        animalRepo.deleteById(id);
    }

    @DeleteMapping("/animal/remove-by-ids")
    public void deleteAnimalsByIds(@RequestParam("ids") List<Long> ids) {
        try {
            animalRepo.deleteAllById(ids);
        } catch (EmptyResultDataAccessException e) {
            log.warn("Один или несколько из удаляемых параметров " + ids.toString() + " отстутствует в системе");
        }
    }

}
