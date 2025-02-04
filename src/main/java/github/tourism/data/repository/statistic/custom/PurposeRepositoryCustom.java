package github.tourism.data.repository.statistic.custom;

import github.tourism.data.entity.statistic.Purpose_Statistic;
import github.tourism.web.dto.statistic.PurposeTop7DTO;

import java.util.List;

public interface PurposeRepositoryCustom {

    List<Purpose_Statistic> findTop7ByMonth(int year, int month);
    List<PurposeTop7DTO> findTop7ByYear(int year);

}
