package github.tourism.web.dto.map;


import github.tourism.data.entity.map.Street;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MapsDTO {

    private int mapsId;
    private String mapsName;
//    private String mapsImage;
    private String mapGoo;
//    private String mapsAddress;
//    private String mapsDescription;


    public MapsDTO(Street street) {
        this.mapsId = street.getId();
        this.mapsName = street.getStreetName();
//        this.mapsImage = street.;
        this.mapGoo = street.getGoo();
    }
}
