package github.tourism.data.repository.statistic;

import github.tourism.data.entity.statistic.Purpose_Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurposeRepository extends JpaRepository<Purpose_Statistic, Integer> {
}
