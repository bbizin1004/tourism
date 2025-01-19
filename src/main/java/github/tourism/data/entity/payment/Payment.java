package github.tourism.data.entity.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import github.tourism.data.entity.order.Order;
import github.tourism.data.entity.user.User;
import github.tourism.web.dto.payment.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
@Table(name = "Payment")
public class Payment {

    @Id @GeneratedValue
    @Column(name = "payment_id")
    private Integer paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "imp_uid")
    private String impUid;

    @Column(name = "merchant_uid")
    private String merchantUid;

    @Column(name = "payment_card")
    private String paymentCard;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    @Column(name = "payment_date")
    private Timestamp paymentDate;

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;


    // paymentDate -> String  변환
//    public String getPaymentDate() {
//        if (paymentDate != null) {
//            // 포맷팅할 형식 지정 (예: "yyyy-MM-dd HH:mm:ss")
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            return sdf.format(new Date(paymentDate.getTime()));
//        }
//        return null;
//    }

    // from 팩토리 메서드
    public static Payment from(Order order, String impUid, String merchantUid, PaymentStatus paymentStatus, String paymentCard) {
        BigDecimal totalPrice = order != null ? order.calculateTotalPrice() : BigDecimal.ZERO;

        return Payment.builder()
                .order(order)
                .impUid(impUid)
                .merchantUid(merchantUid)
                .paymentStatus(paymentStatus)
                .paymentCard(paymentCard)
                .totalPrice(totalPrice)
                .build();
    }






}

