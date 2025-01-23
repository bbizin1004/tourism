package github.tourism.data.repository.statistic.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import github.tourism.data.entity.statistic.Gender_Statistic;
import github.tourism.web.dto.statistic.GenderTop7ByYearAndMonthDTO;
import jakarta.persistence.EntityManager;

import java.util.List;

import static github.tourism.data.entity.statistic.QGender_Statistic.gender_Statistic;

public class Gender_RepositoryImpl implements GenderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Gender_RepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Gender_Statistic> findTop7ByYearAndMonth(int year,int month){

        return queryFactory
                .selectFrom(gender_Statistic)
                .where(gender_Statistic.year.eq(year)
                        .and(gender_Statistic.month.eq(month)))
                .orderBy(gender_Statistic.total_population.desc())
                .limit(7)
                .fetch();
    };

    @Override
    public List<GenderTop7ByYearAndMonthDTO> findTop7CountriesByYear(int year) {
        return queryFactory
                .select(Projections.constructor(GenderTop7ByYearAndMonthDTO.class,
                                gender_Statistic.year,
                                gender_Statistic.country,
                                gender_Statistic.total_population.sum(),
                                gender_Statistic.male_population.sum(),
                                gender_Statistic.female_population.sum()))
                .from(gender_Statistic)
                .where(gender_Statistic.year.eq(year))
                .groupBy(gender_Statistic.country)
                .orderBy(gender_Statistic.total_population.sum().desc())
                .limit(7)
                .fetch();
    }


}
