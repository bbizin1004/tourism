package github.tourism.data.repository.order;

import github.tourism.data.entity.order.Order;
import github.tourism.data.entity.order.OrderItems;
import github.tourism.web.dto.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Payment 와 연관된 주문을 조회
//    @Query("SELECT o FROM Order o JOIN Payment p ON o.orderId = p.order.orderId WHERE p.merchantUid = :merchantUid")
//    Optional<Order> findByMerchantUid(String merchantUid);


    // 특정 사용자의 특정 상태 주문 조회
    @Query("SELECT o FROM Order o WHERE o.user.userId = :userId AND o.status = :status")
    List<Order> findByUserIdAndStatus(Integer userId, OrderStatus orderStatus);

    // 특정 주문에 포함된 주문 항목 조회
    @Query("SELECT oi FROM OrderItems oi WHERE oi.order.orderId = :orderId")
    List<OrderItems> findOrderItemsByOrderId(@Param("orderId") Integer orderId);
}
