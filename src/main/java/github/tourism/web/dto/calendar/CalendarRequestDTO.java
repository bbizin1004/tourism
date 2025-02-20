package github.tourism.web.dto.calendar;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CalendarRequestDTO {
    @NotNull(message = "userId는 필수 값입니다.")
    private Integer userId;

    @NotNull(message = "mapId는 필수 값입니다.")
    private Integer mapId;

    @NotNull(message = "tourStartDate는 필수 값입니다.")
    private LocalDate tourStartDate;

    @NotNull(message = "scheduleTime은 필수 값입니다.")
    private LocalDateTime scheduleTime;

    @NotNull(message = "scheduleEndTime은 필수 값입니다.")
    private LocalDateTime scheduleEndTime;

    private Integer favPlaceId;

//    @NotNull(message = "placeName은 필수 값입니다.")
//    private String placeName;

    private String memo;

    private String placeName;
    private String placeImage;
    private String placeLocation;


    // 생성자
    public CalendarRequestDTO(Integer userId, Integer mapId, LocalDate tourStartDate, LocalDateTime scheduleTime, LocalDateTime scheduleEndTime, Integer favPlaceId, String memo, String placeName, String placeImage, String placeLocation) {
        this.userId = userId;
        this.mapId = mapId;
        this.tourStartDate = tourStartDate;
        this.scheduleTime = scheduleTime;
        this.scheduleEndTime = scheduleEndTime;
        this.favPlaceId = favPlaceId;
//        this.placeName = placeName;
        this.memo = memo;
        this.placeName = placeName;
        this.placeImage = placeImage;
        this.placeLocation = placeLocation;
    }
}
