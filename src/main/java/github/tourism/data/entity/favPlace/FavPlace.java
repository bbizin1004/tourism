package github.tourism.data.entity.favPlace;


import github.tourism.data.entity.map.Map;
import github.tourism.data.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Fav_place")
public class FavPlace {

    @Id
    @Column(name = "fav_place_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favPlaceId ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "map_id")
    private Map map;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "place_name")
    private String placeName;

    @Column(name = "place_location")
    private String placeLocation;

    @Column(name = "place_image")
    private String placeImage;

    @Column(name = "place_details_info")
    private String placeDetailsInfo;

    @Column(name = "like_status")
    private Boolean likeStatus;
}
