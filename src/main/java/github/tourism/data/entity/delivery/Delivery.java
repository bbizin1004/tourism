package github.tourism.data.entity.delivery;

import github.tourism.data.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "Deliveryinfo")
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_info_id")
    private Integer deliveryId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "receiver_phone")
    private String receiverPhone;

    @Column(name = "main_address")
    private String mainAddress;

    @Column(name = "details_address")
    private String detailAddress;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "expected_delivery")
    private Timestamp deliveryTime;
}
