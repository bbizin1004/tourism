package github.tourism.web.dto.statistic;

import jakarta.persistence.GeneratedValue;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class GenderResponseDTO {
    private Integer year;
    private Integer month;
    private String continent;
    private String country;
    private Integer totalPopulation;
    private Integer previousTotalPopulation;
    private BigDecimal totalIncrement;
    private BigDecimal malePopulation;
    private BigDecimal maleRatio;
    private BigDecimal femalePopulation;
    private BigDecimal femaleRatio;
}
