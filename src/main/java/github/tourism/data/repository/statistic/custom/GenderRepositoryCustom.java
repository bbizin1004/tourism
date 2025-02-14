package github.tourism.data.repository.statistic.custom;

import github.tourism.data.entity.statistic.Gender_Statistic;
import github.tourism.web.dto.statistic.GenderTop7DTO;

import java.util.List;

public interface GenderRepositoryCustom {

    List<GenderTop7DTO> findTop7ByYearAndMonth(int year, int month);
    List<GenderTop7DTO> findTop7CountriesByYear(int year);
}
