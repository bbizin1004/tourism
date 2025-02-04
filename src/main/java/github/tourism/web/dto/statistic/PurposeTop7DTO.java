package github.tourism.web.dto.statistic;

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

    //년도별 데이터 조회 생성자
    public PurposeTop7DTO(String country, int totalPopulation, int travelPopulation, int commercialPopulation, int publicPopulation, int studyPopulation, int etcPopulation) {
        this.country = country;
        this.total_population = totalPopulation;
        this.travel_population = travelPopulation;
        this.commercial_population = commercialPopulation;
        this.public_population = publicPopulation;
        this.study_population = studyPopulation;
        this.etc_population = etcPopulation;
    }
}


