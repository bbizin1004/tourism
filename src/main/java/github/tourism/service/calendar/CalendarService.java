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
    private final CalendarDetailsRepository calendarDetailsRepository;

    //  날짜 범위로 일정 조회
    public List<Calendar> getCalendarEntries(Integer userId, LocalDateTime startTime, LocalDateTime endTime) {
        return calendarRepository.findAllByUserIdAndScheduleDateBetween(userId, startTime, endTime);

    }

    // 캘린더 항목 생성
    public Calendar createCalendar(Calendar calendar) {
        return calendarRepository.save(calendar);
    }
}
