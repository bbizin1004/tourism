package github.tourism.web.dto.statistic;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurposeResponseDTO {
    private int year;
    private int month;
    private String continent;
    private String country;
    private Integer totalPopulation;
    private Integer previousTotalPopulation;
    private Double totalIncrement;
    private Double totalRatio;
    private Integer travelPopulation;
    private Double travelRatio;
    private Integer commercialPopulation;
    private Double commercialRatio;
    private Integer publicPopulation;
    private Integer publicRatio;
    private Integer studyPopulation;
    private Double studyRatio;
    private Integer etcPopulation;
    private Double etcRatio;
}
