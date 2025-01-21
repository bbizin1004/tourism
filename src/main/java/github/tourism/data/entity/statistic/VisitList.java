package github.tourism.data.entity.statistic;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "visitlist")
@Builder
@ToString
public class VisitList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitlist_id")
    private Integer visitListId;
    @Column(name = "rank")
    private Integer rank;
    @Column(name = "city_name")
    private String city_name;
    @Column(name = "township_name")
    private String township_name;
    @Column(name = "administrative_name")
    private String administrative_name;
    @Column(name = "visit_num")
    private Integer visit_num;
    @Column(name = "previous_total_population")
    private Integer previous_total_population;
    @Column(name = "ratio")
    private Double ratio;
    @Column(name = "year_month")
    private String year_month;

}
