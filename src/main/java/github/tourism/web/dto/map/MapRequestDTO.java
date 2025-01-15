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

}
