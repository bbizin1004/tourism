package github.tourism.data.repository.calendar;

import github.tourism.data.entity.calendar.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository <Calendar, Integer> {
//    날짜 범위로 Calendar 조회
    List<Calendar> findAllByUserIdAndScheduleTimeBetween(int userId, LocalDateTime startTime, LocalDateTime endTime);

    List<Calendar> findByUserIdAndTourStartDate(int userId, LocalDate tourStartDate);
}
