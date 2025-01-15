package github.tourism.web.dto.map;


import github.tourism.data.entity.map.Map;
import lombok.Data;

@Data
public class MapsDTO {

    private int mapId;
    private String place_Name;
    private String place_image;
    private String place_info;
    private String place_location;
    private String category;

    public MapsDTO(Map map) {
        this.mapId = map.getMapId();
        this.place_Name = map.getPlace_name();
        this.place_image = map.getPlace_image();
        this.place_info = map.getPlace_info();
        this.place_location = map.getPlace_location();
        this.category = map.getCategory();
    }
}
