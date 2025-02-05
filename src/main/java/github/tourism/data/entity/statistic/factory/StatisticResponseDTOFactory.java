package github.tourism.data.entity.statistic.factory;

import github.tourism.data.entity.statistic.Gender_Statistic;
import github.tourism.data.entity.statistic.Purpose_Statistic;
import github.tourism.data.entity.statistic.RankPlace;
import github.tourism.data.entity.statistic.VisitList;
import github.tourism.web.dto.statistic.*;

public interface StatisticResponseDTOFactory {
    GenderResponseDTO createGenderResponseDTO(Gender_Statistic statistic);
    PurposeResponseDTO createPurposeResponseDTO(Purpose_Statistic statistic);
    RankPlaceResponseDTO createRankPlaceResponseDTO(RankPlace rankPlace);
    VisitListResponseDTO createVisitListResponseDTO(VisitList visitList);
}
