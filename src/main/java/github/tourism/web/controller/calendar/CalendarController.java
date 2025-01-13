package github.tourism.web.controller.calendar;

import github.tourism.data.entity.calendar.Calendar;
import github.tourism.service.calendar.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

//    캘린더 날짜 범위 데이터 가져오기
    @GetMapping("/{userId}")
    public ResponseEntity<List<Calendar>> getUserCalendar(
            @PathVariable int userId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        try {
            // Parse the start and end dates
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);

            // Convert to LocalDateTime at the start of the day
            LocalDateTime startTime = LocalDate.parse(startDate).atStartOfDay();
            LocalDateTime endTime = LocalDate.parse(endDate).atStartOfDay().plusDays(1);
            // Fetch the calendar entries based on the user and date range
            List<Calendar> calendars = calendarService.getCalendarEntries(userId, startTime, endTime);
            return ResponseEntity.ok(calendars);

        } catch (DateTimeParseException e) {
            // Return bad request if dates are invalid
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 캘린더 생성
    @PostMapping
    public ResponseEntity<Calendar> createCalendar(@RequestBody Calendar calendar) {
        try {
            // Create the new calendar entry
            Calendar createdCalendar = calendarService.createCalendar(calendar);
            // Return the created calendar with a 201 status code
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCalendar);
        } catch (Exception e) {
            // If any error occurs during creation, return a bad request
            return ResponseEntity.badRequest().body(null);
        }
    }
}

