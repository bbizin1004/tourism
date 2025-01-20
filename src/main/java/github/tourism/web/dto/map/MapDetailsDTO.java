package github.tourism.web.dto.map;

import github.tourism.data.entity.map.Map;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MapDetailsDTO {

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
    private boolean isFavorite;

    public MapDetailsDTO(Map map) {
        this.mapId = map.getMapId();
        this.place_name = map.getPlace_name();
        this.place_image = map.getPlace_image();
        this.place_info = map.getPlace_info();
        this.operation_time = map.getOperation_time();
        this.close_date = map.getClose_date();
        this.place_location = map.getPlace_location();
        this.place_tel = map.getPlace_tel();
        this.category = map.getCategory();
        this.lat = map.getLat();
        this.lng = map.getLng();
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}
