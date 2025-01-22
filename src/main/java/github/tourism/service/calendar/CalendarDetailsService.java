package github.tourism.service.calendar;

import github.tourism.data.entity.calendar.Calendar;
import github.tourism.data.entity.calendar.CalendarDetails;
import github.tourism.data.entity.favPlace.FavPlace;
import github.tourism.data.repository.calendar.CalendarDetailsRepository;
import github.tourism.data.repository.calendar.CalendarRepository;
import github.tourism.data.repository.favPlace.FavPlaceRepository;
import github.tourism.web.dto.calendar.CalendarDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarDetailsService {


    private final CalendarDetailsRepository calendarDetailsRepository;
    private final CalendarRepository calendarRepository;
    private final FavPlaceRepository favPlaceRepository;


    public List<CalendarDetailsDTO> getCalendarDetailsByUserAndDate(int userId, LocalDate tourStartDate) {
        // 1. userId와 tourStartDate로 Calendar 객체를 가져옴
        List<Calendar> calendarList = calendarRepository.findByUserIdAndTourStartDate(userId, tourStartDate);

        // 2. 해당 calendarId들을 기반으로 CalendarDetails를 조회
        List<CalendarDetails> calendarDetailsList = calendarDetailsRepository.findByCalendar_CalendarIdInAndScheduleTimeBetween(
                calendarList.stream().map(Calendar::getCalendarId).collect(Collectors.toList()),
                tourStartDate.atStartOfDay(),
                tourStartDate.plusDays(1).atStartOfDay()
        );

        // 3. CalendarDetails를 DTO로 변환하여 반환
        return calendarDetailsList.stream()
                .map(cd -> {
                    // 해당 장소 정보 (place_name, place_image, place_location) 가져오기
                    FavPlace favPlace = favPlaceRepository.findById(cd.getCalendar().getFavPlaceId())
                            .orElseThrow(() -> new RuntimeException("FavPlace 를 찾을수 없습니다."));

                    return new CalendarDetailsDTO(
                            cd.getCalendarDetailsId(),
                            cd.getPlaceName(),  // CalendarDetails의 placeName
                            cd.getScheduleTime(),
                            cd.getScheduleEndTime(),
                            cd.getPlaceImage(),
                            cd.getPlaceLocation(),
                            cd.getMemo(),
                            favPlace.getPlaceName(),  // FavPlace에서 가져온 placeName
                            favPlace.getPlaceImage(), // FavPlace에서 가져온 placeImage
                            favPlace.getPlaceLocation() // FavPlace에서 가져온 placeLocation
                    );
                })
                .collect(Collectors.toList());
    }
}


//    // 1. 특정 캘린더의 세부 정보 조회
//    public List<CalendarDetails> getCalendarDetails(int calendarId) {
//        if (!calendarDetailsRepository.existsById(calendarId)) {
//            throw new IllegalArgumentException("해당 캘린더가 존재하지 않습니다. ID: " + calendarId);
//        }
//        return calendarDetailsRepository.findAllByCalendarId(calendarId);
//    }
//
//    // 상세 정보 추가
//    public List<CalendarDetails> addCalendarDetails(List<CalendarDetails> calendarDetails) {
//        if (calendarDetails == null || calendarDetails.isEmpty()) {
//            throw new IllegalArgumentException("세부 정보가 비어 있습니다.");
//        }
//
//        // 검증 로직 추가
//        for (CalendarDetails detail : calendarDetails) {
//            if (detail.getCalendarId() == null || detail.getPlaceName() == null) {
//                throw new IllegalArgumentException("필수 세부 정보가 누락되었습니다.");
//            }
//        }
//
//        return calendarDetailsRepository.saveAll(calendarDetails);
//    }

