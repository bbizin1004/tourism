package github.tourism.service.calendar;

import github.tourism.data.entity.calendar.Calendar;
import github.tourism.data.entity.calendar.CalendarDetails;
import github.tourism.data.repository.calendar.CalendarDetailsRepository;
import github.tourism.data.repository.calendar.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    //  1. 특정 날짜 범위로 캘린더 항목 조회
    public List<Calendar> getCalendarEntries(Integer userId, LocalDateTime startTime, LocalDateTime endTime) {
        if (userId == null) {
            throw new IllegalArgumentException("사용자 ID가 null입니다.");
        }
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("시작 날짜가 종료 날짜보다 이후입니다.");
        }
        return calendarRepository.findAllByUserIdAndScheduleDateBetween(userId, startTime, endTime);

    }

    // 2. 여러 개의 캘린더 항목 생성
    public List<Calendar> createCalendar(List<Calendar> calendars) {
        if (calendars == null || calendars.isEmpty()) {
            throw new IllegalArgumentException("캘린더 목록이 비어 있습니다.");
        }
        // 중복 또는 필수 필드 검증
        for (Calendar calendar : calendars) {
            if (calendar.getUserId() == null || calendar.getTourStartDate() == null || calendar.getTourEndDate() == null ) {
                throw new IllegalArgumentException("캘린더의 필수 정보가 누락되었습니다.");
            }
            // 시작 날짜가 종료 날짜보다 이후인 경우 검증
            if(calendar.getTourStartDate().isAfter(calendar.getTourEndDate())){
                throw new IllegalArgumentException("여행 시작 날짜가 종료 날짜보다 이후일 수 없습니다.");
            }
        }
        // 저장
        return calendarRepository.saveAll(calendars);
    }
}
