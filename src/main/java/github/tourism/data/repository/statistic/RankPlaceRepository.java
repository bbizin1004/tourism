package github.tourism.data.repository.statistic;

import github.tourism.data.entity.statistic.RankPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankPlaceRepository extends JpaRepository<RankPlace,Integer> {
}
