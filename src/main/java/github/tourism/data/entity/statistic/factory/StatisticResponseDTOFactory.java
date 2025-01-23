package github.tourism.data.entity.statistic.factory;

import github.tourism.data.entity.statistic.Gender_Statistic;
import github.tourism.data.entity.statistic.Purpose_Statistic;
import github.tourism.data.entity.statistic.RankPlace;
import github.tourism.data.entity.statistic.VisitList;
import github.tourism.web.dto.statistic.GenderResponseDTO;
import github.tourism.web.dto.statistic.PurposeResponseDTO;
import github.tourism.web.dto.statistic.RankPlaceResponseDTO;
import github.tourism.web.dto.statistic.VisitListResponseDTO;

public interface StatisticResponseDTOFactory {
    GenderResponseDTO createGenderResponseDTO(Gender_Statistic statistic);

    PurposeResponseDTO createPurposeResponseDTO(Purpose_Statistic statistic);
    RankPlaceResponseDTO createRankPlaceResponseDTO(RankPlace rankPlace);
    VisitListResponseDTO createVisitListResponseDTO(VisitList visitList);
}
