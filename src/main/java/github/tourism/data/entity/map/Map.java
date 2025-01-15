package github.tourism.data.entity.map;


import github.tourism.data.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Map {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "map_id")
    private Integer mapId;

    private String place_name;
    private String place_image;
    private String place_info;
    private String operation_time;
    private String close_date;
    private String place_location;
    private String place_tel;
    private String category;
    private BigDecimal lat;
    private BigDecimal lng;


}