package github.tourism.web.dto.statistic;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurposeTop7DTO {
    private String country;
    private int total_population;
    private int travel_population;
    private int commercial_population;
    private int public_population;
    private int study_population;
    private int etc_population;


    @QueryProjection
    public PurposeTop7DTO(String country, int total_population, int travel_population, int commercial_population, int public_population, int study_population, int etc_population) {
        this.country = country;
        this.total_population = total_population;
        this.travel_population = travel_population;
        this.commercial_population = commercial_population;
        this.public_population = public_population;
        this.study_population = study_population;
        this.etc_population = etc_population;
    }
}


