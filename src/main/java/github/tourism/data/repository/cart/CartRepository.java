package github.tourism.data.repository.cart;

import github.tourism.data.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT c FROM Cart c JOIN FETCH c.goods WHERE c.user.userId = :userId")
    List<Cart> findCartByUserId(@Param("userId") Integer userId);

    @Query("SELECT c FROM Cart c JOIN FETCH c.goods WHERE c.cartId = :cartId")  // goods 데이터 초기화
    Optional<Cart> findByIdWithGoods(@Param("cartId") Integer cartId);
}
