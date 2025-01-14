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

    // 1. 특정 캘린더의 세부 정보 조회
    public List<CalendarDetails> getCalendarDetails(int calendarId) {
        if (!calendarDetailsRepository.existsById(calendarId)) {
            throw new IllegalArgumentException("해당 캘린더가 존재하지 않습니다. ID: " + calendarId);
        }
        return calendarDetailsRepository.findAllByCalendarId(calendarId);
    }

    // 상세 정보 추가
    public List<CalendarDetails> addCalendarDetails(List<CalendarDetails> calendarDetails) {
        if (calendarDetails == null || calendarDetails.isEmpty()) {
            throw new IllegalArgumentException("세부 정보가 비어 있습니다.");
        }

        // 검증 로직 추가
        for (CalendarDetails detail : calendarDetails) {
            if (detail.getCalendarId() == null || detail.getPlaceName() == null) {
                throw new IllegalArgumentException("필수 세부 정보가 누락되었습니다.");
            }
        }

        return calendarDetailsRepository.saveAll(calendarDetails);
    }
}
