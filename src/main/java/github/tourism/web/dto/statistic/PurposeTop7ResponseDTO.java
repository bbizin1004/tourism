package github.tourism.web.dto.statistic;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurposeTop7ResponseDTO {
    private int year;
    private int month;
    private String country;
    private int totalPopulation;
    private int preTotalPopulation;
    private int travelPopulation;
    private int commercialPopulation;
    private int publicPopulation;
    private int studyPopulation;
    private int etcPopulation;

    //년도별 데이터 조회 생성자
    public PurposeTop7ResponseDTO(int year,  String country, int totalPopulation, int preTotalPopulation, int travelPopulation, int commercialPopulation, int publicPopulation, int studyPopulation, int etcPopulation) {
        this.year = year;
        this.country = country;
        this.totalPopulation = totalPopulation;
        this.preTotalPopulation = preTotalPopulation;
        this.travelPopulation = travelPopulation;
        this.commercialPopulation = commercialPopulation;
        this.publicPopulation = publicPopulation;
        this.studyPopulation = studyPopulation;
        this.etcPopulation = etcPopulation;
    }
}


