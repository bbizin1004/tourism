package github.tourism.data.entity.calendar;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Calendar")
public class Calendar {

    @Id
    @Column(name = "calendar_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer calendarId ;

    @Column(name = "user_id")
    private Integer userId ;

    @Column(name = "map_id")
    private Integer mapId ;

    @Column(name = "fav_place_id")
    private Integer favPlaceId ;

    @Column(name = "tour_start_date")
    private LocalDate tourStartDate ;

    @Column(name = "tour_end_date")
    private LocalDate tourEndDate ;

    @Column(name = "place_name")
    private String placeName ;

//    @Column(name = "schedule_time")
//    private String scheduleTime ;

    @Column(name = "schedule_date")
    private LocalDateTime scheduleDate ;

    @Column(name = "memo")
    private String memo ;


}
