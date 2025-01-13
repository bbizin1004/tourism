package github.tourism.data.repository.calendar;

import github.tourism.data.entity.calendar.CalendarDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarDetailsRepository extends JpaRepository<CalendarDetails, Integer> {
    // 특정 Calendar에 연결된 상세 데이터 조회
    List<CalendarDetails> findAllByCalendarId(int calendarId);
}
