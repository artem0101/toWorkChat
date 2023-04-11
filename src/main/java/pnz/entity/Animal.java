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

import lombok.Data;

@Data
@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @SequenceGenerator(name = "animal_seq",
            sequenceName = "animal_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private AnimalType type;

    @Column(name = "is_predator")
    private Boolean isPredator;

    private Animal() {}

    public static class AnimalBuilder {
        private final Animal newAnimal;

        public AnimalBuilder() {
            this.newAnimal = new Animal();
        }

        public AnimalBuilder withName(String name) {
            newAnimal.name = name;
            return this;
        }

        public AnimalBuilder withType(AnimalType type) {
            newAnimal.type = type;
            return this;
        }

        public AnimalBuilder withIsPredator(Boolean isPredator) {
            newAnimal.isPredator = isPredator;
            return this;
        }

        public Animal build() {
            return newAnimal;
        }
    }

}
