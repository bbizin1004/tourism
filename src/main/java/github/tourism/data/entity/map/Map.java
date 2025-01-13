package github.tourism.data.entity.map;


import github.tourism.data.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter @Setter //나중에 setter 지울예정.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Map {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "map_id")
    private Integer mapId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private BigDecimal latitude;
    private BigDecimal longitude;
    private String place_name;
    private String place_image;
    private String place_info;
    private String place_details_info;
    private String operation_time;
    private String operation_date;
    private String place_location;
    private String place_contact_num;


}