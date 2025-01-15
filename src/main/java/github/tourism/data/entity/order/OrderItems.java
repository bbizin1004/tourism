package github.tourism.data.entity.order;

import github.tourism.data.entity.cart.Cart;
import github.tourism.data.entity.goods.Goods;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItems {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Integer orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Integer price;

    // 팩토리 메서드
    public static OrderItems from(Order order, Cart cartItem) {
        Goods goods = cartItem.getGoods();
        return OrderItems.builder()
                .order(order)
                .goods(goods)
                .quantity(cartItem.getQuantity())
                .price(goods.getPrice() * cartItem.getQuantity())
                .build();
    }
}
