package github.tourism.data.repository.statistic;

import github.tourism.data.entity.statistic.Gender_Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Gender_Repository extends JpaRepository<Gender_Statistic,Integer> {


    //연도별로 상위7개 총인구 순위 가져오기
    @Query("SELECT g FROM Gender_Statistic g ORDER BY g.year DESC ,g.total_population DESC ")
    List<Gender_Statistic> findTop7ByYearOrderByTotalPopulationDesc();
}
