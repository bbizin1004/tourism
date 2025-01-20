package github.tourism.web.dto.statistic;

import github.tourism.data.entity.statistic.Gender_Statistic;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenderStatisticDTO {
    private Integer id;
    private int year;
    private int totalPopulation;



    public GenderStatisticDTO(Gender_Statistic statistic) {
        this.id = statistic.getId();
        this.year = statistic.getYear();
        this.totalPopulation = statistic.getTotal_population();
    }


}
