package github.tourism.service.statistic;

import github.tourism.data.entity.statistic.Gender_Statistic;
import github.tourism.data.entity.statistic.Purpose_Statistic;
import github.tourism.data.repository.statistic.Gender_Repository;
import github.tourism.data.repository.statistic.PurposeRepository;
import github.tourism.web.dto.statistic.GenderStatisticDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static java.util.stream.Collectors.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StatisticService {

    private final Gender_Repository gender_Repository;
    private final PurposeRepository purpose_Repository;


    //성별 통계 전체조회
    public List<Gender_Statistic> getAllGenderStatistics() {
        return gender_Repository.findAll();
    }

    //목적 통계 전체 조회
    public List<Purpose_Statistic> getAllPurposeStatistics() {
        return purpose_Repository.findAll();
    }

    //연도에따라 상위7개 총인구 순위 가져오기
    public List<GenderStatisticDTO> getTop7TotalPopulationByYear() {
        List<Gender_Statistic> genderStatistics = gender_Repository.findTop7ByYearOrderByTotalPopulationDesc();

        List<GenderStatisticDTO> result = genderStatistics.stream()
                .map(o -> new GenderStatisticDTO(o))
                .collect(toList());

        return result;

    }





}
