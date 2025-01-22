package github.tourism.web.controller.calendar;

import github.tourism.service.calendar.CalendarDetailsService;
import github.tourism.web.dto.calendar.CalendarDetailsDTO;
import github.tourism.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar/schedule")
public class CalendarDetailsController {
    private final CalendarDetailsService calendarDetailsService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CalendarDetailsDTO>> getSchedulesByDate(
            @PathVariable int userId,
            @RequestParam String tourStartDate) {
        try {
            // 날짜 파싱
            LocalDate startDate = LocalDate.parse(tourStartDate);

            // 해당 날짜에 등록된 일정 조회
            List<CalendarDetailsDTO> calendarDetailsList = calendarDetailsService.getCalendarDetailsByUserAndDate(userId, startDate);
            return calendarDetailsList.isEmpty()
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.ok(calendarDetailsList);

        } catch (DateTimeParseException e) {
            // 잘못된 날짜 형식 예외 처리
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/all/{userId}")
    public ResponseEntity<List<CalendarDetailsDTO>> getAllSchedulesByUser(
            @PathVariable int userId) {
        try {
            // 해당 날짜에 등록된 일정 조회
            List<CalendarDetailsDTO> calendarDetailsList = calendarDetailsService.getCalendarDetailsByUser(userId);
            return calendarDetailsList.isEmpty()
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.ok(calendarDetailsList);

        } catch (DateTimeParseException e) {
            // 잘못된 날짜 형식 예외 처리
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{calendarId}")
    public ResponseEntity<String> deleteScheduleByUser(
            @PathVariable int calendarId,
            @RequestParam int userId) {
        try {
            // 해당 날짜에 등록된 일정 조회
            try {
                calendarDetailsService.deleteCalendarDetailsByUserIdAndCalendarId(userId, calendarId);
            } catch (NotFoundException e) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok("Entry deleted successfully");
        } catch (DateTimeParseException e) {
            // 잘못된 날짜 형식 예외 처리
            return ResponseEntity.badRequest().body(null);
        }
    }




//    // 1. 특정 캘린더의 상세 정보 조회
//    @GetMapping("/{calendarId}")
//    public ResponseEntity<List<CalendarDetails>> getCalendarDetails(@PathVariable int calendarId) {
//        List<CalendarDetails> details = calendarDetailsService.getCalendarDetails(calendarId);
//        return ResponseEntity.ok(details);
//    }
//
//    // 2. 여러개의 세부 정보 등록
//    @PostMapping
//    public ResponseEntity<List<CalendarDetails>> saveCalendarDetails(@RequestBody List<CalendarDetails> calendarDetails) {
//        try {
//            if (calendarDetails.isEmpty()) {
//                throw new IllegalArgumentException("세부 정보 목록이 비어 있습니다.");
//            }
//            //  저장
//            List<CalendarDetails> savedDetails = calendarDetailsService.addCalendarDetails(calendarDetails);
//            return ResponseEntity.status(HttpStatus.CREATED).body(savedDetails);
//
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(null);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
}
