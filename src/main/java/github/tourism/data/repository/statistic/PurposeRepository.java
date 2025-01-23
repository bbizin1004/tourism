package github.tourism.data.repository.statistic;

import github.tourism.data.entity.statistic.Purpose_Statistic;
import github.tourism.data.repository.statistic.custom.PurposeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurposeRepository extends JpaRepository<Purpose_Statistic, Integer>, PurposeRepositoryCustom {
}
