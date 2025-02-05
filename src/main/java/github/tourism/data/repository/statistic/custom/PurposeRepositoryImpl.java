package github.tourism.data.repository.statistic.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import github.tourism.web.dto.statistic.PurposeTop7DTO;
import github.tourism.web.dto.statistic.QPurposeTop7DTO;
import jakarta.persistence.EntityManager;

import java.util.List;

import static github.tourism.data.entity.statistic.QPurpose_Statistic.purpose_Statistic;

public class PurposeRepositoryImpl implements PurposeRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public PurposeRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<PurposeTop7DTO> findTop7ByMonth(int year, int month) {
        return queryFactory
                .select(new QPurposeTop7DTO(purpose_Statistic.country, purpose_Statistic.total_population,
                        purpose_Statistic.travel_population, purpose_Statistic.commercial_population,
                        purpose_Statistic.public_population, purpose_Statistic.study_population, purpose_Statistic.etc_population))
                .from(purpose_Statistic)
                .where(purpose_Statistic.year.eq(year)
                        .and(purpose_Statistic.month.eq(month)))
                .orderBy(purpose_Statistic.total_population.desc())
                .limit(7)
                .fetch();
    }

    @Override
    public List<PurposeTop7DTO> findTop7ByYear(int year) {
        return queryFactory
                .select(Projections.constructor(PurposeTop7DTO.class,
                        purpose_Statistic.country,
                        purpose_Statistic.total_population.sum(),
                        purpose_Statistic.travel_population.sum(),
                        purpose_Statistic.commercial_population.sum(),
                        purpose_Statistic.public_population.sum(),
                        purpose_Statistic.study_population.sum(),
                        purpose_Statistic.etc_population.sum()))
                .from(purpose_Statistic)
                .where(purpose_Statistic.year.eq(year))
                .groupBy(purpose_Statistic.country,purpose_Statistic.year)
                .orderBy(purpose_Statistic.total_population.sum().desc())
                .limit(7)
                .fetch();

    }




}
