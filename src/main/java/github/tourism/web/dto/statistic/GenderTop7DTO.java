package github.tourism.web.dto.statistic;

import com.querydsl.core.annotations.QueryProjection;
import github.tourism.data.entity.statistic.Gender_Statistic;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenderTop7DTO {
    private String country;
    private int total_Population;
    private int male_Population;
    private int female_Population;

    @QueryProjection
    public GenderTop7DTO(String country, int total_Population, int male_Population,
                         int female_Population) {
        this.country = country;
        this.total_Population = total_Population;
        this.male_Population = male_Population;
        this.female_Population = female_Population;
    }
}
