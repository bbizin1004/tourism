package github.tourism.web.dto.statistic;

import github.tourism.data.entity.statistic.Gender_Statistic;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenderStatisticDTO {
    private Integer id;
    private int year;
    private int month;
    private String country;
    private int totalPopulation;
    private int malePopulation;
    private int femalePopulation;



    public GenderStatisticDTO(Gender_Statistic statistic) {
        this.id = statistic.getId();
        this.year = statistic.getYear();
        this.month = statistic.getMonth();
        this.country = statistic.getCountry();
        this.totalPopulation = statistic.getTotal_population();
        this.malePopulation = statistic.getMale_population();
        this.femalePopulation = statistic.getFemale_population();
    }


}
