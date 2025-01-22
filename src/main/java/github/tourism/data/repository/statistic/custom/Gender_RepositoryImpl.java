package github.tourism.data.repository.statistic.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import github.tourism.data.entity.statistic.Gender_Statistic;
import github.tourism.data.entity.statistic.QGender_Statistic;
import github.tourism.data.repository.statistic.Gender_Repository;
import jakarta.persistence.EntityManager;

import java.util.List;

import static github.tourism.data.entity.statistic.QGender_Statistic.*;

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
}
