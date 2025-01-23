package github.tourism.service.statistic;

import github.tourism.data.entity.statistic.Gender_Statistic;
import github.tourism.data.entity.statistic.Purpose_Statistic;
import github.tourism.data.entity.statistic.RankPlace;
import github.tourism.data.entity.statistic.VisitList;
import github.tourism.data.entity.statistic.factory.StatisticResponseDTOFactory;
import github.tourism.data.repository.statistic.Gender_Repository;
import github.tourism.data.repository.statistic.PurposeRepository;
import github.tourism.data.repository.statistic.RankPlaceRepository;
import github.tourism.data.repository.statistic.VisitListStatisticRepository;
import github.tourism.web.dto.statistic.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StatisticService {

    private final Gender_Repository gender_Repository;
    private final PurposeRepository purpose_Repository;
    private final RankPlaceRepository rankPlaceRepository;
    private final VisitListStatisticRepository visitListStatisticRepository;

    private final StatisticResponseDTOFactory statisticResponseDTOFactory;

    // 성별 통계 전체 조회
    public List<GenderResponseDTO> getGenderStatistics() {
        List<Gender_Statistic> genderStatistics = gender_Repository.findAll();
        return genderStatistics.stream()
                .map(statisticResponseDTOFactory::createGenderResponseDTO)
                .collect(Collectors.toList());
    }

    // 목적 통계 전체 조회
    public List<PurposeResponseDTO> getPurposeStatistics() {
        List<Purpose_Statistic> purposeStatistics = purpose_Repository.findAll();
        return purposeStatistics.stream()
                .map(statisticResponseDTOFactory::createPurposeResponseDTO)
                .collect(Collectors.toList());
    }

    public List<RankPlaceResponseDTO> getRankPlace() {
        List<RankPlace> rankPlaces = rankPlaceRepository.findAll();
        return rankPlaces.stream()
                .map(statisticResponseDTOFactory::createRankPlaceResponseDTO)
                .collect(Collectors.toList());
    }

    public List<VisitListResponseDTO> getVisitListStatistics() {
        List<VisitList> visitLists = visitListStatisticRepository.findAll();
        return visitLists.stream()
                .map(statisticResponseDTOFactory::createVisitListResponseDTO)
                .collect(Collectors.toList());
    }
    //연도와 월에따라 상위7개 총인구 순위 가져오기
    public List<GenderStatisticDTO> getTop7(int year, int month) {
        List<Gender_Statistic> genderStatistics = gender_Repository.findTop7ByYearAndMonth(year, month);

        return genderStatistics.stream()
                .map(GenderStatisticDTO::new)
                .collect(Collectors.toList());
    }

}
