package github.tourism.data.repository.statistic;

import github.tourism.data.entity.statistic.Gender_Statistic;
import github.tourism.data.repository.statistic.custom.GenderRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Gender_Repository extends JpaRepository<Gender_Statistic,Integer>, GenderRepositoryCustom {

}
