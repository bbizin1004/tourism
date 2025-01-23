package github.tourism.data.entity.statistic.factory;

import github.tourism.data.entity.statistic.Gender_Statistic;
import github.tourism.data.entity.statistic.Purpose_Statistic;
import github.tourism.data.entity.statistic.RankPlace;
import github.tourism.data.entity.statistic.VisitList;
import github.tourism.web.dto.statistic.GenderResponseDTO;
import github.tourism.web.dto.statistic.PurposeResponseDTO;
import github.tourism.web.dto.statistic.RankPlaceResponseDTO;
import github.tourism.web.dto.statistic.VisitListResponseDTO;

public class StatisticResponseDTOFactoryImpl implements StatisticResponseDTOFactory {
    @Override
    public GenderResponseDTO createGenderResponseDTO(Gender_Statistic statistic) {
        GenderResponseDTO dto = new GenderResponseDTO();
        dto.setYear(statistic.getYear());
        dto.setMonth(statistic.getMonth());
        dto.setContinent(statistic.getContinent());
        dto.setCountry(statistic.getCountry());
        dto.setTotalPopulation(statistic.getTotal_population());
        dto.setPreviousTotalPopulation(statistic.getPrevious_total_population());
        dto.setTotalIncrement(statistic.getTotal_increment());
        dto.setMalePopulation(statistic.getMale_population());
        dto.setMaleRatio(statistic.getMale_ratio());
        dto.setFemalePopulation(statistic.getFemale_population());
        dto.setFemaleRatio(statistic.getFemale_ratio());
        return dto;
    }

    @Override
    public PurposeResponseDTO createPurposeResponseDTO(Purpose_Statistic statistic) {
        PurposeResponseDTO dto = new PurposeResponseDTO();
        dto.setYear(statistic.getYear());
        dto.setMonth(statistic.getMonth());
        dto.setContinent(statistic.getContinent());
        dto.setCountry(statistic.getCountry());
        dto.setTotalPopulation(statistic.getTotal_population());
        dto.setPreviousTotalPopulation(statistic.getPrevious_total_population());
        dto.setTotalIncrement(statistic.getTotal_increment());
        dto.setTotalRatio(statistic.getTotal_ratio());
        dto.setTravelPopulation(statistic.getTravel_population());
        dto.setTravelRatio(statistic.getTravel_ratio());
        dto.setCommercialPopulation(statistic.getCommercial_population());
        dto.setCommercialRatio(statistic.getCommercial_ratio());
        dto.setPublicPopulation(statistic.getPublic_population());
        dto.setPublicRatio(statistic.getPublic_ratio());
        dto.setStudyPopulation(statistic.getStudy_population());
        dto.setStudyRatio(statistic.getStudy_ratio());
        dto.setEtcPopulation(statistic.getEtc_population());
        dto.setEtcRatio(statistic.getEtc_ratio());
        return dto;
    }

    @Override
    public RankPlaceResponseDTO createRankPlaceResponseDTO(RankPlace rankPlace) {
        RankPlaceResponseDTO dto = new RankPlaceResponseDTO();
        dto.setRank(rankPlace.getRank());
        dto.setCityName(rankPlace.getCity_name());
        dto.setTownshipName(rankPlace.getTownship_name());
        dto.setPlaceName(rankPlace.getPlace_name());
        dto.setYearMonth(rankPlace.getYear_month());
        dto.setVisitNum(rankPlace.getVisit_num());
        dto.setVisitTotalNum(rankPlace.getVisit_total_num());
        return dto;
    }

    @Override
    public VisitListResponseDTO createVisitListResponseDTO(VisitList visitList) {
        VisitListResponseDTO dto = new VisitListResponseDTO();
        dto.setRank(visitList.getRank());
        dto.setCityName(visitList.getCity_name());
        dto.setTownshipName(visitList.getTownship_name());
        dto.setAdministrativeName(visitList.getAdministrative_name());
        dto.setVisitNum(visitList.getVisit_num());
        dto.setPreviousTotalPopulation(visitList.getPrevious_total_population());
        dto.setRatio(visitList.getRatio());
        dto.setYearMonth(visitList.getYear_month());
        return dto;
    }
}
