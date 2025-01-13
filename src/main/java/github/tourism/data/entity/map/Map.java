package github.tourism.data.entity.map;

import github.tourism.data.entity.user.User;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Map")
public class Map {
    @Id @GeneratedValue
    @Column(name = "map_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private double latitude;
    private double longitude;
    private String place_name;
    private String place_image;
    private String place_info;
    private String place_details_info;
    private String operation_time;
    private String operation_date;
    private String place_location;
    private String place_contact_num;
}
