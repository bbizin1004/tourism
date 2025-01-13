package github.tourism.data.entity.calendar;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Calendar_details")
public class CalendarDetails {
    @Id
    @Column(name = "calendar_details_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer calendarDetailsId ;

    @Column(name = "calendar_id")
    private Integer calendarId ;

    @Column(name = "place_name")
    private String placeName ;

    @Column(name = "place_image")
    private String placeImage ;

    @Column(name = "operation_time")
    private String operationTime ;

    @Column(name = "operation_date")
    private String operationDate ;

    @Column(name = "day_off")
    private String dayOff ;

    @Column(name = "place_location")
    private String placeLocation ;

    @Column(name = "place_contact_num")
    private String placeContactNum ;

    @Column(name = "place_details_info")
    private String placeDetailsInfo ;

//    @Column(name = "schedule_time")
//    private String scheduleTime ;

    @Column(name = "schedule_date")
    private LocalDateTime scheduleDate ;

    @Column(name = "memo")
    private String memo ;

}
