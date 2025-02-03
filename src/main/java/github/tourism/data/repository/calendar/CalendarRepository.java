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
    // 중복 일정 등록 방지 기능 : 같은 시간에 여러 일정 등록 방지!
    // 같은 userId, tourStartDate, scheduleTime이 존재하는지 확인하는 메서드 추가
    @Query("SELECT COUNT(c) > 0 FROM Calendar c " +
            "WHERE c.userId = :userId " +
            "AND c.tourStartDate = :tourStartDate " +
            "AND c.scheduleTime = :scheduleTime")
    boolean existsByUserAndTourStartDateAndTime(@Param("userId") Integer userId,
                                                @Param("tourStartDate") LocalDate tourStartDate,
                                                @Param("scheduleTime") LocalDateTime scheduleTime);

//    날짜 범위로 Calendar 조회
    List<Calendar> findAllByUserIdAndScheduleTimeBetween(int userId, LocalDateTime startTime, LocalDateTime endTime);

    // 특정 사용자(userId)가 등록한 여행 날짜 목록을 조회
    @Query("SELECT DISTINCT c.tourStartDate " +
    "FROM Calendar c " +
    "WHERE c.userId = :userId")
    List<LocalDate> findDistinctTourStartDatesByUserId(@Param("userId") Integer userId);
}
