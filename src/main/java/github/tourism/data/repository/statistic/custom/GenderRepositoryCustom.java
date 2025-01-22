package github.tourism.data.repository.statistic.custom;

import github.tourism.data.entity.statistic.Gender_Statistic;

import java.util.List;

public interface GenderRepositoryCustom {

    List<Gender_Statistic> findTop7ByYearAndMonth(int year, int month);
}
