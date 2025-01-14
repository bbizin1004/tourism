package github.tourism.web.dto.map;

import github.tourism.data.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class MapRequestDTO {
    private Long id;
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

    public MapRequestDTO(Long id, User user, BigDecimal latitude, BigDecimal longitude, String place_name, String place_image, String place_info, String place_details_info, String operation_time, String operation_date, String place_location, String place_contact_num) {
        this.id = id;
        this.user = user;
        this.latitude = latitude;
        this.longitude = longitude;
        this.place_name = place_name;
        this.place_image = place_image;
        this.place_info = place_info;
        this.place_details_info = place_details_info;
        this.operation_time = operation_time;
        this.operation_date = operation_date;
        this.place_location = place_location;
        this.place_contact_num = place_contact_num;
    }
}
