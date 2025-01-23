package github.tourism.data.repository.statistic.custom;

import github.tourism.data.entity.statistic.Gender_Statistic;
import github.tourism.web.dto.statistic.GenderTop7ByYearAndMonthDTO;

import java.util.List;

public interface GenderRepositoryCustom {

    List<Gender_Statistic> findTop7ByYearAndMonth(int year, int month);
    List<GenderTop7ByYearAndMonthDTO> findTop7CountriesByYear(int year);
}
