package github.tourism.web.controller.calendar;

import github.tourism.data.entity.calendar.CalendarDetails;
import github.tourism.service.calendar.CalendarDetailsService;
import github.tourism.service.calendar.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar-details")
public class CalendarDetailsController {
    private final CalendarDetailsService calendarDetailsService;

    // 특정 캘린더의 상세 정보 조회
    @GetMapping("/{calendarId}")
    public ResponseEntity<List<CalendarDetails>> getCalendarDetails(@PathVariable int calendarId) {
        List<CalendarDetails> details = calendarDetailsService.getCalendarDetails(calendarId);
        return ResponseEntity.ok(details);
    }

   // 상세 정보 저장
   @PostMapping
   public ResponseEntity<CalendarDetails> saveCalendarDetails(@RequestBody CalendarDetails calendarDetail) {
        CalendarDetails savedDetail = calendarDetailsService.addCalendarDetails(calendarDetail);
        return ResponseEntity.ok(savedDetail);
   }
}
