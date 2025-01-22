package github.tourism.data.entity.statistic;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "gender_combined")
public class Gender_Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer year;
    private Integer month;
    private String continent;
    private String country;
    private Integer total_population;
    private Integer previous_total_population;
    private BigDecimal total_increasement;
    private Integer male_population;
    private BigDecimal male_ratio;
    private Integer female_population;
    private BigDecimal female_ratio;



}
