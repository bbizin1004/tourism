package github.tourism.web.dto.map;


import github.tourism.data.entity.map.Map;
import lombok.Data;

@Data
public class MapsDTO {

    private int map_id;
    private String place_name;
    private String place_image;
    private String place_location;
    //프론트 쪽에서 미구현
//    private String category;

    public MapsDTO(Map map) {
        this.map_id = map.getMapId();
        this.place_name = map.getPlace_name();
        this.place_image = map.getPlace_image();
        this.place_location = map.getPlace_location();
//        this.category = map.getCategory();
    }
}
