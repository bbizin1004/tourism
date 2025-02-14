package github.tourism.data.repository.cart;

import github.tourism.data.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT c FROM Cart c JOIN FETCH c.goods WHERE c.user.userId = :userId")
    List<Cart> findCartByUserId(@Param("userId") Integer userId);   // 사용자 장바구니 항목 전체 조회

    @Query("SELECT c FROM Cart c JOIN FETCH c.goods WHERE c.cartId = :cartId")  // goods 데이터 초기화
    Optional<Cart> findByIdWithGoods(@Param("cartId") Integer cartId);


    List<Cart> findByCartIdIn(List<Integer> cartItemIds);  // 사용자가 선택한 장바구니 항목 ID 리스트로 조회

    // 특정 사용자와 특정 상품에 대한 장바구니 항목 삭제
    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.goods.goodId IN :goodsIds")
    void deleteByGoodsIdIn(@Param("goodsIds") List<Integer> orderItemGoodsIds);
}
