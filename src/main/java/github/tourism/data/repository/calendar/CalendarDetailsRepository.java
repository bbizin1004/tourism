package github.tourism.data.repository.calendar;

import github.tourism.data.entity.calendar.CalendarDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CalendarDetailsRepository extends JpaRepository<CalendarDetails, Integer> {
    // CalendarDetails에서 Calendar의 calendarId를 참조
    List<CalendarDetails> findByCalendar_CalendarIdInAndScheduleTimeBetween(List<Integer> calendarIds, LocalDateTime startTime, LocalDateTime endTime);

    List<CalendarDetails> findByCalendar_CalendarIdAndScheduleTimeBetween(int calendarId, LocalDateTime startTime, LocalDateTime endTime);

    // calendarId에 맞는 모든 상세 데이터 조회
    List<CalendarDetails> findAllByCalendar_CalendarId(int calendarId);
}
