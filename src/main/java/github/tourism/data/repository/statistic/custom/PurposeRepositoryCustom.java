package github.tourism.data.repository.statistic.custom;

import github.tourism.data.entity.statistic.Purpose_Statistic;
import github.tourism.web.dto.statistic.PurposeTop7ResponseDTO;

import java.util.List;

public interface PurposeRepositoryCustom {

    List<Purpose_Statistic> findTop7ByMonth(int year, int month);
    List<PurposeTop7ResponseDTO> findTop7ByYear(int year);

}
