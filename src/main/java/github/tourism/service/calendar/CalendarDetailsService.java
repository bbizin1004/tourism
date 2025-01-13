package github.tourism.service.calendar;

import github.tourism.data.entity.calendar.CalendarDetails;
import github.tourism.data.repository.calendar.CalendarDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarDetailsService {

    private final CalendarDetailsRepository calendarDetailsRepository;

    // 특정 Calendar에 연결된 상세 정보 조회
    public List<CalendarDetails> getCalendarDetails(int calendarId) {
        return calendarDetailsRepository.findAllByCalendarId(calendarId);
    }

    // 상세 정보 추가
    public CalendarDetails addCalendarDetails(CalendarDetails calendarDetail) {
        return calendarDetailsRepository.save(calendarDetail);
    }
}
