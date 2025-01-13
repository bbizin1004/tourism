package github.tourism.data.repository.goods;

import github.tourism.data.entity.goods.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Integer> {

    //세일 판매기간에만 판매되는 상품만 추출
    @Query("SELECT g FROM Goods g WHERE :currentTime BETWEEN g.saleStartTime AND g.saleEndTime")
    Page<Goods> findAvailableGoods(@Param("currentTime") Timestamp currentTime, Pageable pageable);

    Page<Goods> findAllByCategory(Pageable pageable, String category);
}
