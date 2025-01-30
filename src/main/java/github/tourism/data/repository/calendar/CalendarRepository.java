package github.tourism.data.repository.calendar;

import github.tourism.data.entity.calendar.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository <Calendar, Integer> {
//    날짜 범위로 Calendar 조회
    List<Calendar> findAllByUserIdAndScheduleTimeBetween(int userId, LocalDateTime startTime, LocalDateTime endTime);

    // 특정 사용자(userId)가 등록한 여행 날짜 목록을 조회
    @Query("SELECT DISTINCT c.tourStartDate " +
    "FROM Calendar c " +
    "WHERE c.userId = :userId")
    List<LocalDate> findDistinctTourStartDatesByUserId(@Param("userId") Integer userId);
}
