package github.tourism.web.dto.statistic;

import com.querydsl.core.annotations.QueryProjection;
import github.tourism.data.entity.statistic.Gender_Statistic;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenderTop7ByYearAndMonthDTO {
    private int year;
    private int month;
    private String country;
    private int totalPopulation;
    private int malePopulation;
    private int femalePopulation;

    public GenderTop7ByYearAndMonthDTO(Gender_Statistic statistic) {
        this.year = statistic.getYear();
        this.month = statistic.getMonth();
        this.country = statistic.getCountry();
        this.totalPopulation = statistic.getTotal_population();
        this.malePopulation = statistic.getMale_population();
        this.femalePopulation = statistic.getFemale_population();
    }

    public GenderTop7ByYearAndMonthDTO(int year, String country, int totalPopulation, int malePopulation, int femalePopulation) {
        this.year = year;
        this.country = country;
        this.totalPopulation = totalPopulation;
        this.malePopulation = malePopulation;
        this.femalePopulation = femalePopulation;
    }
}
