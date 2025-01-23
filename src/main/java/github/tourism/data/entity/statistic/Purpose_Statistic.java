package github.tourism.data.entity.statistic;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "purpose_all_combined")
public class Purpose_Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purpose_id")
    private Integer purposeId;

    private int year;
    private int month;
    private String continent;
    private String country;
    private Integer total_population;
    private Integer previous_total_population;
    @Column(name = "total_increasement")
    private Double total_increment;
    private Double total_ratio;
    private Integer travel_population;
    private Double travel_ratio;
    private Integer commercial_population;
    private Double commercial_ratio;
    private Integer public_population;
    private Integer public_ratio;
    private Integer study_population;
    private Double study_ratio;
    private Integer etc_population;
    private Double etc_ratio;

}
