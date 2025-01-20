package github.tourism.data.entity.order;

import github.tourism.data.entity.delivery.Delivery;
import github.tourism.data.entity.user.User;
import github.tourism.web.dto.order.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;

    @CreationTimestamp
    @Column(name = "order_date")
    private Timestamp orderDate;

    @OneToMany(mappedBy = "order")
    private List<OrderItems> orderItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_info_id")
    private Delivery deliveryInfo;


    public void setStatus(OrderStatus status) {
        this.status = status;
    }


    // 주문의 총 금액을 계산하는 메서드
    public BigDecimal calculateTotalPrice() {
        return orderItems.stream()
                .map(OrderItems::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
