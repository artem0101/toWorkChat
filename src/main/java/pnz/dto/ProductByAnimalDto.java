package pnz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductByAnimalDto {

    private String animalName;
    private String productName;
    private Long productCount;

}
