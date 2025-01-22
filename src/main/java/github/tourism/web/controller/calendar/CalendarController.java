package github.tourism.web.controller.calendar;

import github.tourism.data.entity.calendar.Calendar;
import github.tourism.data.entity.map.Map;
import github.tourism.service.calendar.CalendarService;
import github.tourism.web.dto.calendar.CalendarRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

//    캘린더 날짜 범위 데이터 가져오기
//    @GetMapping("/{userId}")
//    public ResponseEntity<List<Calendar>> getUserCalendar(
//            @PathVariable int userId,
//            @RequestParam String startDate,
//            @RequestParam String endDate) {
//        try {
//            // 날짜 파싱
//            LocalDate start = LocalDate.parse(startDate);
//            LocalDate end = LocalDate.parse(endDate);
//
//            // 날짜를 LocalDateTime으로 변환
//            LocalDateTime startTime = LocalDate.parse(startDate).atStartOfDay();
//            LocalDateTime endTime = LocalDate.parse(endDate).atStartOfDay().plusDays(1);
//            // 서비스 호출
//            List<Calendar> calendars = calendarService.getCalendarEntries(userId, startTime, endTime);
//            return ResponseEntity.ok(calendars);
//
//        } catch (DateTimeParseException e) {
//            //  잘못된 날짜 형식 예외 처리
//            return ResponseEntity.badRequest().body(null);
//        }
//    }

    // 단일 또는 여러 개의 캘린더 항목(여행 일정) 등록
    // 단일 캘린더 생성
    @PostMapping("/single")
    public ResponseEntity<Calendar> createSingleCalendar(@RequestBody CalendarRequestDTO calendarRequestDTO) {
        Calendar createdCalendar = calendarService.createSingleCalendar(calendarRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCalendar);
    }

    // 여러 캘린더 생성
    @PostMapping("/multiple")
    public ResponseEntity<List<Calendar>> createMultipleCalendars(@RequestBody List<CalendarRequestDTO> calendarRequestDTOS) {
        List<Calendar> createdCalendars = calendarService.createMultipleCalendars(calendarRequestDTOS);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCalendars);
    }
}

