package pnz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalculationRequirementFoodsDto {

    private String productName;
    private Long expectedProductCount;
    private Long actualProductCount;
    private Long neededProductCount;

}
