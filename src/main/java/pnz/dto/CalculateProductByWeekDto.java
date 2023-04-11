package pnz.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalculateProductByWeekDto {

    private Date startDate;
    private Date endDate;
    private List<CalculationRequirementFoodsDto> calculationRequirementFoodsDtos;

}
