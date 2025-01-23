package github.tourism.web.dto.statistic;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RankPlaceResponseDTO {
    private Integer rank;
    private String cityName;
    private String townshipName;
    private String placeName;
    private String yearMonth;
    private Integer visitNum;
    private Integer visitTotalNum;
}
