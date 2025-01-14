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
            // 날짜 파싱
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);

            // 날짜를 LocalDateTime으로 변환
            LocalDateTime startTime = LocalDate.parse(startDate).atStartOfDay();
            LocalDateTime endTime = LocalDate.parse(endDate).atStartOfDay().plusDays(1);
            // 서비스 호출
            List<Calendar> calendars = calendarService.getCalendarEntries(userId, startTime, endTime);
            return ResponseEntity.ok(calendars);

        } catch (DateTimeParseException e) {
            //  잘못된 날짜 형식 예외 처리
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 여러 개의 캘린더 항목(여행 일정) 등록
    @PostMapping
    public ResponseEntity<List<Calendar>> createCalendar(@RequestBody List<Calendar> calendars) {
        try {
            // 캘린더 항목이 비어있을 경우 예외 처리
            if (calendars.isEmpty()) {
                throw new IllegalArgumentException("캘린더 목록이 비어 있습니다.");
            }
            // 캘린더 저장
            List<Calendar> savedCalendars = calendarService.createCalendar(calendars);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCalendars);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // 입력값 검증 예외 처리
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);   // 일반적인 예외 처리

        }
    }
}

