package github.tourism.data.repository.statistic;

import github.tourism.data.entity.statistic.VisitList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitListStatisticRepository extends JpaRepository<VisitList,Integer> {
}
