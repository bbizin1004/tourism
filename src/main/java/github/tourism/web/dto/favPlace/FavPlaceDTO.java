package github.tourism.web.dto.favPlace;

import github.tourism.data.entity.favPlace.FavPlace;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FavPlaceDTO {
    private Integer favPlaceId;
    private Integer mapId;
    private String placeName;
    private String placeLocation;
    private String placeImage;
    private String placeDetailsInfo;
    private Boolean likeStatus;
    private String userName;
    private Integer likemarkCount; // Map에서 가져와야함!


    // 엔티티에서 DTO로 변환하는 생성자
    public FavPlaceDTO(FavPlace favPlace) {
        this.favPlaceId = favPlace.getFavPlaceId();
        this.mapId = favPlace.getMap().getMapId();
        this.placeName = favPlace.getPlaceName();
        this.placeLocation = favPlace.getPlaceLocation();
        this.placeImage = favPlace.getPlaceImage();
        this.placeDetailsInfo = favPlace.getPlaceDetailsInfo();
        this.likeStatus = favPlace.getLikeStatus();

        // Map의 likemarkCount 가져옴
        this.likemarkCount = favPlace.getMap().getLikemarkCount();

        // Lazy 로딩 방지: User가 null이 아닐 경우만 데이터를 가져옴
        if (favPlace.getUser() != null) {
            this.userName = favPlace.getUser().getUserName(); // User 엔티티의 필드 사용 (Username)
        }
    }
}
