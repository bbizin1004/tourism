package github.tourism.web.controller.calendar;

import github.tourism.data.entity.calendar.CalendarDetails;
import github.tourism.service.calendar.CalendarDetailsService;
import github.tourism.service.calendar.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar-details")
public class CalendarDetailsController {
    private final CalendarDetailsService calendarDetailsService;

    // 1. 특정 캘린더의 상세 정보 조회
    @GetMapping("/{calendarId}")
    public ResponseEntity<List<CalendarDetails>> getCalendarDetails(@PathVariable int calendarId) {
        List<CalendarDetails> details = calendarDetailsService.getCalendarDetails(calendarId);
        return ResponseEntity.ok(details);
    }

    // 2. 여러개의 세부 정보 등록
    @PostMapping
    public ResponseEntity<List<CalendarDetails>> saveCalendarDetails(@RequestBody List<CalendarDetails> calendarDetails) {
        try {
            if (calendarDetails.isEmpty()) {
                throw new IllegalArgumentException("세부 정보 목록이 비어 있습니다.");
            }
            //  저장
            List<CalendarDetails> savedDetails = calendarDetailsService.addCalendarDetails(calendarDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDetails);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
