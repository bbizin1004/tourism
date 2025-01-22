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

    // Calendar 엔티티와 연관된 외래 키를 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id", referencedColumnName = "calendar_id", insertable = false, updatable = false)
    private Calendar calendar;

//    @Column(name = "calendar_id")
//    private Integer calendarId ;

    @Column(name = "place_name")
    private String placeName ;

    @Column(name = "place_image")
    private String placeImage ;

    @Column(name = "place_location")
    private String placeLocation ;

//    @Column(name = "place_contact_num")
//    private String placeContactNum ;

    @Column(name = "schedule_time")
    private LocalDateTime scheduleTime ;

    @Column(name = "schedule_end_time")
    private LocalDateTime scheduleEndTime ;

    @Column(name = "memo")
    private String memo ;

}
