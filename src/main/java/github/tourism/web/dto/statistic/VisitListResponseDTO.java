package github.tourism.web.dto.statistic;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VisitListResponseDTO {

    private Integer rank;
    private String cityName;
    private String townshipName;
    private String administrativeName;
    private Integer visitNum;
    private Integer previousTotalPopulation;
    private Double ratio;
    private String yearMonth;
}
