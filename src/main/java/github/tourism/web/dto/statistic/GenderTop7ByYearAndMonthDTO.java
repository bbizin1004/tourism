package github.tourism.web.dto.statistic;

import com.querydsl.core.annotations.QueryProjection;
import github.tourism.data.entity.statistic.Gender_Statistic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenderTop7ByYearAndMonthDTO {
    private String country;
    private int total_Population;
    private int male_Population;
    private int female_Population;

    public GenderTop7ByYearAndMonthDTO(Gender_Statistic statistic) {
        this.country = statistic.getCountry();
        this.total_Population = statistic.getTotal_population();
        this.male_Population = statistic.getMale_population();
        this.female_Population = statistic.getFemale_population();
    }

}
