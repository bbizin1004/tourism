package github.tourism.data.repository.statistic.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import github.tourism.data.entity.statistic.Purpose_Statistic;
import github.tourism.data.entity.statistic.QGender_Statistic;
import github.tourism.data.entity.statistic.QPurpose_Statistic;
import github.tourism.web.dto.statistic.PurposeTop7ResponseDTO;
import jakarta.persistence.EntityManager;

import java.util.List;

import static github.tourism.data.entity.statistic.QPurpose_Statistic.*;

public class PurposeRepositoryImpl implements PurposeRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public PurposeRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Purpose_Statistic> findTop7ByMonth(int year, int month) {
        return queryFactory
                .selectFrom(purpose_Statistic)
                .where(purpose_Statistic.year.eq(year)
                        .and(purpose_Statistic.month.eq(month)))
                .orderBy(purpose_Statistic.total_population.desc())
                .limit(7)
                .fetch();
    }

    @Override
    public List<PurposeTop7ResponseDTO> findTop7ByYear(int year) {
        return queryFactory
                .select(Projections.constructor(PurposeTop7ResponseDTO.class,
                        purpose_Statistic.year,
                        purpose_Statistic.country,
                        purpose_Statistic.total_population.sum(),
                        purpose_Statistic.previous_total_population.sum(),
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
